package cn.xc.handmade.mybatis.session.impl;

import cn.xc.handmade.mybatis.binding.MapperRegistry;
import cn.xc.handmade.mybatis.session.SqlSession;
import cn.xc.handmade.mybatis.session.SqlSessionFactory;

public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(mapperRegistry);
    }

}
