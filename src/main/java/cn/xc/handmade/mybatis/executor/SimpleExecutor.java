package cn.xc.handmade.mybatis.executor;

import cn.xc.handmade.mybatis.executor.statement.StatementHandler;
import cn.xc.handmade.mybatis.mapping.BoundSql;
import cn.xc.handmade.mybatis.mapping.MappedStatement;
import cn.xc.handmade.mybatis.session.Configuration;
import cn.xc.handmade.mybatis.session.ResultHandler;
import cn.xc.handmade.mybatis.transaction.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SimpleExecutor extends BaseExecutor {

    public SimpleExecutor(Configuration configuration, Transaction transaction) {
        super(configuration, transaction);
    }

    @Override
    protected <E> List<E> doQuery(MappedStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql) {
        try {
            StatementHandler handler = configuration.newStatementHandler(this, ms, parameter, resultHandler, boundSql);
            Connection conn = transaction.getConnection();
            Statement stmt = handler.prepare(conn);
            handler.parameterize(stmt);
            return handler.query(stmt, resultHandler);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
