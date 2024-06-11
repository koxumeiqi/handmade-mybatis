package cn.xc.handmade.mybatis.session;

public interface SqlSession {

    /**
     * 映射器代理对象
     *
     * @param type
     * @param <T>  对应的mapper 接口
     * @return mapper代理对象
     */
    <T> T getMapper(Class<T> type);

    <T> T selectOne(String statement, Object parameter);

    Object selectOne(String statement);

    Configuration getConfiguration();

}
