package cn.xc.handmade.mybatis.session;

import cn.xc.handmade.mybatis.builder.xml.XmlConfigBuilder;
import cn.xc.handmade.mybatis.session.impl.DefaultSqlSessionFactory;

import java.io.Reader;

/**
 * SqlSessionFactory 构建工厂
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader) {
        XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }

    public SqlSessionFactory build(Configuration config) {
        return new DefaultSqlSessionFactory(config);
    }

}
