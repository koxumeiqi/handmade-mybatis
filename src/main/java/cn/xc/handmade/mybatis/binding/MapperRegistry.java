package cn.xc.handmade.mybatis.binding;

import cn.hutool.core.lang.ClassScanner;
import cn.xc.handmade.mybatis.session.Configuration;
import cn.xc.handmade.mybatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;

/**
 * 映射器注册
 */
public class MapperRegistry {

    // mapper 的本地缓存
    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();

    private final Configuration configuration;

    public MapperRegistry(Configuration configuration) {
        this.configuration = configuration;
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
        if (mapperProxyFactory == null) {
            throw new RuntimeException("Type " + type + " is not known to the MapperRegistry.");
        }
        try {
            return mapperProxyFactory.newInstance(sqlSession);
        } catch (Exception e) {
            throw new RuntimeException("Error getting mapper instance. Cause: " + e, e);
        }
    }

    public <T> void addMapper(Class<T> type) {
        if (type.isInterface()) {
            if (hasMapper(type))
                throw new RuntimeException("Type " + type + " is already known to the MapperRegistry.");
            knownMappers.put(type, new MapperProxyFactory<>(type));
        }
    }

    public void addMappers(String packageName) {
        ClassScanner.scanPackage(packageName)
                .forEach(this::addMapper);
    }

    public <T> boolean hasMapper(Class<T> type) {
        return knownMappers.containsKey(type);
    }


}
