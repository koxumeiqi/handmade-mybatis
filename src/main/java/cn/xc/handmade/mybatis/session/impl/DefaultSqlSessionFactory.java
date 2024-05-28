package cn.xc.handmade.mybatis.session.impl;

import cn.xc.handmade.mybatis.session.Configuration;
import cn.xc.handmade.mybatis.session.SqlSession;
import cn.xc.handmade.mybatis.session.SqlSessionFactory;

public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }

}
