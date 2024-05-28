package cn.xc.handmade.mybatis.mapping;


import cn.xc.handmade.mybatis.session.Configuration;

import java.util.Map;

public class MappedStatement {

    private Configuration configuration;

    // 唯一标识,namespace+id
    private String id;
    // sql语句类型
    private SqlCommandType sqlCommandType;
    // 参数类型
    private String parameterType;
    // 结果类型
    private String resultType;
    // 对应sql文本
    private String sql;
    private Map<Integer, String> parameter;

    public static class Builder {

        private MappedStatement mappedStatement = new MappedStatement();

        public Builder(Configuration configuration,
                       String id,
                       SqlCommandType sqlCommandType,
                       String parameterType,
                       String resultType,
                       String sql,
                       Map<Integer, String> parameter) {
            mappedStatement.configuration = configuration;
            mappedStatement.id = id;
            mappedStatement.sqlCommandType = sqlCommandType;
            mappedStatement.parameterType = parameterType;
            mappedStatement.resultType = resultType;
            mappedStatement.sql = sql;
            mappedStatement.parameter = parameter;
        }

        public MappedStatement build() {
            assert mappedStatement.configuration != null;
            assert mappedStatement.id != null;
            return mappedStatement;
        }

    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<Integer, String> getParameter() {
        return parameter;
    }

    public void setParameter(Map<Integer, String> parameter) {
        this.parameter = parameter;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public SqlCommandType getSqlCommandType() {
        return sqlCommandType;
    }

    public void setSqlCommandType(SqlCommandType sqlCommandType) {
        this.sqlCommandType = sqlCommandType;
    }
}
