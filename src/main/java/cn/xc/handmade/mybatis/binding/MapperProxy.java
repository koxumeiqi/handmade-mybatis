package cn.xc.handmade.mybatis.binding;

import cn.xc.handmade.mybatis.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

public class MapperProxy<T> implements InvocationHandler, Serializable {

    private final SqlSession sqlSession;

    private final Class<T> mapperInterfaceClass;

    private final Map<Method, MapperMethod> methodCache;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterfaceClass, Map<Method, MapperMethod> methodCache) {
        this.sqlSession = sqlSession;
        this.mapperInterfaceClass = mapperInterfaceClass;
        this.methodCache = methodCache;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {
            final MapperMethod mapperMethod = this.cachedMapperMethod(method);
            return mapperMethod.execute(sqlSession, args);
        }
    }

    /**
     * 去缓存中找MapperMethod
     */
    private MapperMethod cachedMapperMethod(Method method) {
        MapperMethod mapperMethod = methodCache.get(method);
        if (mapperMethod == null) {
            //找不到才去new
            mapperMethod = new MapperMethod(mapperInterfaceClass, method, sqlSession.getConfiguration());
            methodCache.put(method, mapperMethod);
        }
        return mapperMethod;
    }

}
