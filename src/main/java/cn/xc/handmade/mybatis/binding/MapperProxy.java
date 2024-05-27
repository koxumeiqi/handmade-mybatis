package cn.xc.handmade.mybatis.binding;

import cn.xc.handmade.mybatis.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MapperProxy<T> implements InvocationHandler, Serializable {

    private final SqlSession sqlSession;

    private final Class<T> mapperInterfaceClass;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterfaceClass) {
        this.sqlSession = sqlSession;
        this.mapperInterfaceClass = mapperInterfaceClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {
            return sqlSession.selectOne(method.getName(), args);
        }
    }

}
