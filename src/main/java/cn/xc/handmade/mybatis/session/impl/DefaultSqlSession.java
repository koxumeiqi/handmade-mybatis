package cn.xc.handmade.mybatis.session.impl;

import cn.xc.handmade.mybatis.session.Configuration;
import cn.xc.handmade.mybatis.session.SqlSession;

public class DefaultSqlSession implements SqlSession {

    private final Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }

    @Override
    public Object selectOne(String statement, Object parameter) {
        return "执行的全限定名：" +
                statement +
                "被执行器执行了;入参是：" +
                parameter +
                " 执行的sql语句：" + configuration.getMappedStatement(statement).getSql();
    }

    @Override
    public Object selectOne(String statement) {
        return statement + "被执行器执行了";
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }


}
