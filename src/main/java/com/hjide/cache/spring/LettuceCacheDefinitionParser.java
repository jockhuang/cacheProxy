package com.hjide.cache.spring;

import com.hjide.cache.DefaultCacheImpl;
import com.hjide.cache.client.RedisLettuceClientImpl;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.api.sync.RedisCommands;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSimpleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class LettuceCacheDefinitionParser extends AbstractSimpleBeanDefinitionParser
{
    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder)
    {
        builder.addPropertyValue("cacheClientProxy", createLettuceProxyBeanDefinition(element));
    }

    private BeanDefinition createLettuceProxyBeanDefinition(Element element)
    {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(RedisLettuceClientImpl.class);
        builder.addPropertyValue("commands", createRedisCommandBeanDefinition(element));

        return builder.getBeanDefinition();
    }

    private BeanDefinition createRedisCommandBeanDefinition(Element element)
    {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(RedisCommands.class);
        // 从标签中取出对应的属性值
        String host = element.getAttribute("host");
        String port = element.getAttribute("port");
        String password = element.getAttribute("password");
        String timeout = element.getAttribute("timeout");
//        builder.addPropertyValue("host", host);
//        builder.addPropertyValue("port", port);
        builder.setInitMethodName("sync");


        return builder.getBeanDefinition();
    }

    private BeanDefinition createRedisConnectionBeanDefinition(Element element){
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(StatefulRedisConnection.class);
        return builder.getBeanDefinition();
    }

    @Override
    protected Class getBeanClass(Element element)
    {
        // 返回该标签所定义的类实现,在这里是为了创建出CacheProxy对象
        return DefaultCacheImpl.class;
    }
}
