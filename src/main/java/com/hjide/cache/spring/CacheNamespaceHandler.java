package com.hjide.cache.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class CacheNamespaceHandler extends NamespaceHandlerSupport
{

    @Override
    public void init()
    {
        //自定义标签中的element标签名为client解析注册使用RedisCacheDefinitionParser进行.
        registerBeanDefinitionParser("jedis", new JedisCacheDefinitionParser());
        registerBeanDefinitionParser("lettuce", new LettuceCacheDefinitionParser());
        registerBeanDefinitionParser("lock", new LockCacheDefinitionParser());
    }
}
