package cn.xc.handmade.mybatis.session.impl;

import cn.xc.handmade.mybatis.executor.Executor;
import cn.xc.handmade.mybatis.mapping.MappedStatement;
import cn.xc.handmade.mybatis.session.Configuration;
import cn.xc.handmade.mybatis.session.SqlSession;

import java.util.List;

public class DefaultSqlSession implements SqlSession {

    private final Configuration configuration;

    private Executor executor;

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        MappedStatement ms = configuration.getMappedStatement(statement);
        List<T> list = executor.query(ms, parameter, Executor.NO_RESULT_HANDLER, ms.getBoundSql());
        return list.get(0);
    }

    @Override
    public Object selectOne(String statement) {
        return this.selectOne(statement, null);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }


}
