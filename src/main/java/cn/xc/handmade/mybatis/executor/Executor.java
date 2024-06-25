package cn.xc.handmade.mybatis.executor;

import cn.xc.handmade.mybatis.mapping.BoundSql;
import cn.xc.handmade.mybatis.mapping.MappedStatement;
import cn.xc.handmade.mybatis.session.ResultHandler;
import cn.xc.handmade.mybatis.transaction.Transaction;

import java.sql.SQLException;
import java.util.List;

public interface Executor {

    ResultHandler NO_RESULT_HANDLER = null;

    <E> List<E> query(MappedStatement ms, Object parameter, ResultHandler resultHandler, BoundSql boundSql);

    Transaction getTransaction();

    void commit(boolean required) throws SQLException;

    void rollback(boolean required) throws SQLException;

    void close(boolean forceRollback);


}
