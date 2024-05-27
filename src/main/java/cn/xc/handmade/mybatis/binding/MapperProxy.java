package cn.xc.handmade.mybatis.binding;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

public class MapperProxy<T> implements InvocationHandler, Serializable {

    private final Map<String, String> sqlSessionMap;

    private final Class<T> mapperInterfaceClass;

    public MapperProxy(Map<String, String> sqlSessionMap, Class<T> mapperInterfaceClass) {
        this.sqlSessionMap = sqlSessionMap;
        this.mapperInterfaceClass = mapperInterfaceClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {
            return "你被代理了！ " + sqlSessionMap.get(mapperInterfaceClass.getName() + "." + method.getName());
        }
    }

}
