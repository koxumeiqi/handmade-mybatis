package cn.xc.handmade.mybatis.executor.statement;

import cn.xc.handmade.mybatis.executor.Executor;
import cn.xc.handmade.mybatis.mapping.BoundSql;
import cn.xc.handmade.mybatis.mapping.MappedStatement;
import cn.xc.handmade.mybatis.session.ResultHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SimpleStatementHandler extends BaseStatementHandler {

    public SimpleStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameterObject, ResultHandler resultHandler, BoundSql boundSql) {
        super(executor, mappedStatement, parameterObject, resultHandler, boundSql);
    }

    @Override
    protected Statement instantiateStatement(Connection connection) throws SQLException {
        return connection.createStatement();
    }

    @Override
    public void parameterize(Statement statement) throws SQLException {
        // NoThing
    }

    @Override
    public <E> List<E> query(Statement statement, ResultHandler resultHandler) throws SQLException {
        statement.execute(boundSql.getSql());
        return resultSetHandler.handleResultSets(statement);
    }

}
