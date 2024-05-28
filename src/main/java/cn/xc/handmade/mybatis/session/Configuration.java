package cn.xc.handmade.mybatis.session;

import cn.xc.handmade.mybatis.binding.MapperRegistry;
import cn.xc.handmade.mybatis.mapping.MappedStatement;

import java.util.HashMap;
import java.util.Map;

public class Configuration {

    // 映射注册器
    protected MapperRegistry mapperRegistry = new MapperRegistry(this);

    // 映射的语句对象，存在Map里
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }

    public boolean hasMapper(Class<?> type) {
        return mapperRegistry.hasMapper(type);
    }

    public void addMappers(String packageName) {
        mapperRegistry.addMappers(packageName);
    }

    public MappedStatement getMappedStatement(String id) {
        return mappedStatements.get(id);
    }

    public void addMappedStatement(MappedStatement mappedStatement) {
        mappedStatements.put(mappedStatement.getId(), mappedStatement);
    }
}
