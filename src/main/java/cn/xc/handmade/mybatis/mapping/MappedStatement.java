package cn.xc.handmade.mybatis.mapping;


import cn.xc.handmade.mybatis.session.Configuration;

public class MappedStatement {


    // 唯一标识,namespace+id
    private String id;

    private Configuration configuration;

    private SqlCommandType sqlCommandType;

    private BoundSql boundSql;

    public static class Builder {

        private MappedStatement mappedStatement = new MappedStatement();

        public Builder(Configuration configuration, String id, SqlCommandType sqlCommandType, BoundSql boundSql) {
            mappedStatement.configuration = configuration;
            mappedStatement.id = id;
            mappedStatement.sqlCommandType = sqlCommandType;
            mappedStatement.boundSql = boundSql;
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

    public String getId() {
        return id;
    }

    public SqlCommandType getSqlCommandType() {
        return sqlCommandType;
    }

    public BoundSql getBoundSql() {
        return boundSql;
    }

}
