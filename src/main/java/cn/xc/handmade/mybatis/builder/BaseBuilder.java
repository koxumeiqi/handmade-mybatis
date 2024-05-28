package cn.xc.handmade.mybatis.builder;

import cn.xc.handmade.mybatis.session.Configuration;

public abstract class BaseBuilder {

    protected final Configuration configuration;

    protected BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

}
