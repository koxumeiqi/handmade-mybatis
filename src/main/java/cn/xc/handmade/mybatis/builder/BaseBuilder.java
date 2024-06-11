package cn.xc.handmade.mybatis.builder;

import cn.xc.handmade.mybatis.session.Configuration;
import cn.xc.handmade.mybatis.type.TypeAliasRegistry;

public abstract class BaseBuilder {

    protected final Configuration configuration;

    protected final TypeAliasRegistry typeAliasRegistry;

    protected BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
        this.typeAliasRegistry = this.configuration.getTypeAliasRegistry();
    }

    public Configuration getConfiguration() {
        return configuration;
    }

}
