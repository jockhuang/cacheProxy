package com.hjide.cache.spring;

import com.hjide.cache.DefaultCacheImpl;
import com.hjide.cache.client.RedisLettuceClientImpl;
import com.lambdaworks.redis.RedisURI;
import com.lambdaworks.redis.support.RedisClientFactoryBean;
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
        builder.addPropertyValue("client", createRedisClientFactoryBeanDefinition(element));

        return builder.getBeanDefinition();
    }

    private BeanDefinition createRedisClientFactoryBeanDefinition(Element element)
    {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(RedisClientFactoryBean.class);
        // 从标签中取出对应的属性值

        String password = element.getAttribute("password");
        String timeout = element.getAttribute("timeout");
        builder.addPropertyValue("redisURI", createRedisURIBeanDefinition(element));

        return builder.getBeanDefinition();
    }

    private BeanDefinition createRedisURIBeanDefinition(Element element)
    {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(RedisURI.class);
        String host = element.getAttribute("host");
        String port = element.getAttribute("port");
        builder.addPropertyValue("host", host);
        builder.addPropertyValue("port", port);
        return builder.getBeanDefinition();
    }

    @Override
    protected Class getBeanClass(Element element)
    {
        // 返回该标签所定义的类实现,在这里是为了创建出CacheProxy对象
        return DefaultCacheImpl.class;
    }
}
