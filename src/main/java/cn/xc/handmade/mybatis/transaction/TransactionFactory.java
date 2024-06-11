package cn.xc.handmade.mybatis.transaction;


import cn.xc.handmade.mybatis.session.TransactionIsolationLevel;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * 事务工厂
 */
public interface TransactionFactory {

    /**
     * 根据 Connection 创建 Transaction 对象
     *
     * @param conn
     * @return
     */
    Transaction newTransaction(Connection conn);

    /**
     * 根据数据源和事务隔离级别创建
     *
     * @param dataSource
     * @param level
     * @param autoCommit
     * @return
     */
    Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit);

}
