package cn.xc.handmade.mybatis.session.impl;

import cn.xc.handmade.mybatis.binding.MapperRegistry;
import cn.xc.handmade.mybatis.session.SqlSession;

public class DefaultSqlSession implements SqlSession {

    private final MapperRegistry mapperRegistry;

    public DefaultSqlSession(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return mapperRegistry.getMapper(type, this);
    }

    @Override
    public Object selectOne(String statement, Object parameter) {
        return statement +
                "被执行器执行了;入参是：" +
                parameter;
    }

    @Override
    public Object selectOne(String statement) {
        return statement + "被执行器执行了";
    }

}
