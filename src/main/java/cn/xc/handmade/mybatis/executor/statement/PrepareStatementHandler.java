package cn.xc.handmade.mybatis.executor.statement;

import cn.xc.handmade.mybatis.executor.Executor;
import cn.xc.handmade.mybatis.mapping.BoundSql;
import cn.xc.handmade.mybatis.mapping.MappedStatement;
import cn.xc.handmade.mybatis.session.ResultHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PrepareStatementHandler extends BaseStatementHandler {

    public PrepareStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameterObject, ResultHandler resultHandler, BoundSql boundSql) {
        super(executor, mappedStatement, parameterObject, resultHandler, boundSql);
    }

    @Override
    protected Statement instantiateStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSql());
        return preparedStatement;
    }

    @Override
    public void parameterize(Statement statement) throws SQLException {
        PreparedStatement preparedStatement = (PreparedStatement) statement;
        preparedStatement.setString(1, ((Object[]) parameterObject)[0].toString());
    }

    @Override
    public <E> List<E> query(Statement statement, ResultHandler resultHandler) throws SQLException {
        PreparedStatement preparedStatement = (PreparedStatement) statement;
        preparedStatement.execute();
        return resultSetHandler.<E>handleResultSets(preparedStatement);
    }
}
