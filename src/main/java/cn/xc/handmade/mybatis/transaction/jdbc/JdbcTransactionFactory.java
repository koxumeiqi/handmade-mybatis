package cn.xc.handmade.mybatis.transaction.jdbc;

import cn.xc.handmade.mybatis.session.TransactionIsolationLevel;
import cn.xc.handmade.mybatis.transaction.Transaction;
import cn.xc.handmade.mybatis.transaction.TransactionFactory;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @description JdbcTransaction 工厂
 */
public class JdbcTransactionFactory implements TransactionFactory {

    @Override
    public Transaction newTransaction(Connection conn) {
        return new JdbcTransaction(conn);
    }

    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
        return new JdbcTransaction(dataSource, level, autoCommit);
    }

}
