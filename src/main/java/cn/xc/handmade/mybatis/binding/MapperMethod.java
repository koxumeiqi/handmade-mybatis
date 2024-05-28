package cn.xc.handmade.mybatis.binding;


import cn.xc.handmade.mybatis.mapping.MappedStatement;
import cn.xc.handmade.mybatis.mapping.SqlCommandType;
import cn.xc.handmade.mybatis.session.Configuration;
import cn.xc.handmade.mybatis.session.SqlSession;

import java.lang.reflect.Method;

/**
 * 映射器方法，方法去最外层执行器
 */
public class MapperMethod {

    private final SqlCommand command;

    public MapperMethod(Class<?> mapperInterface, Method method, Configuration configuration) {
        this.command = new SqlCommand(configuration, mapperInterface, method);
    }

    public Object execute(SqlSession sqlSession, Object[] args) {
        Object res = null;
        switch (command.getType()) {
            case INSERT:
                break;
            case DELETE:
                break;
            case UPDATE:
                break;
            case SELECT:
                res = sqlSession.selectOne(command.getName(), args);
                break;
            default:
                throw new RuntimeException("Unknown execution method for: " + command.getName());
        }
        return res;
    }

    /**
     * SQL 指令
     */
    public static class SqlCommand {

        private final String name;
        private final SqlCommandType type;

        public SqlCommand(Configuration configuration, Class<?> mapperInterface, Method method) {
            String statementName = mapperInterface.getName() + "." + method.getName();
            MappedStatement ms = configuration.getMappedStatement(statementName);
            name = ms.getId();
            type = ms.getSqlCommandType();
        }

        public String getName() {
            return name;
        }

        public SqlCommandType getType() {
            return type;
        }
    }


}
