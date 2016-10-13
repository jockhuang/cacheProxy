package com.hjide.cache.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by jock on 15/12/24.
 */
public class CacheNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        //自定义标签中的element标签名为client解析注册使用RedisCacheDefinitionParser进行.
        registerBeanDefinitionParser("redis", new RedisCacheDefinitionParser());
//        registerBeanDefinitionParser("memcached", new RedisCacheDefinitionParser());
        registerBeanDefinitionParser("lock",new LockCacheDefinitionParser());
    }
}
