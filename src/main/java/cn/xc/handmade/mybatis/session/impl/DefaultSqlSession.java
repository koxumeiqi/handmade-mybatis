package cn.xc.handmade.mybatis.session.impl;

import cn.xc.handmade.mybatis.mapping.BoundSql;
import cn.xc.handmade.mybatis.mapping.MappedStatement;
import cn.xc.handmade.mybatis.session.Configuration;
import cn.xc.handmade.mybatis.session.SqlSession;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public <T> T selectOne(String statement, Object parameter) {
        DataSource dataSource = configuration.getEnvironment().getDataSource();
        try {
            Connection conn = dataSource.getConnection();
            MappedStatement mappedStatement = configuration.getMappedStatement(statement);
            BoundSql boundSql = mappedStatement.getBoundSql();
            String sql = boundSql.getSql();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, ((Object[]) parameter)[0].toString());
            ResultSet resultSet = preparedStatement.executeQuery();

            List<T> objList = resultSet2Obj(resultSet, Class.forName(boundSql.getResultType()));
            return objList.size() > 0 ? objList.get(0) : null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private <T> List<T> resultSet2Obj(ResultSet resultSet, Class<?> clazz) {
        List<T> list = new ArrayList<>();
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            // 每次遍历行值
            while (resultSet.next()) {
                T obj = (T) clazz.newInstance();
                for (int i = 1; i <= columnCount; i++) {
                    Object value = resultSet.getObject(i);
                    String columnName = metaData.getColumnName(i);
                    String setMethod = "set" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1);
                    Method method;
                    if (value instanceof Timestamp) {
                        method = clazz.getMethod(setMethod, Date.class);
                    } else {
                        method = clazz.getMethod(setMethod, value.getClass());
                    }
                    method.invoke(obj, value);
                }
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
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
