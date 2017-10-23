package com.hjide.cache.spring;

import com.hjide.cache.DefaultCacheImpl;
import com.hjide.cache.client.RedisCacheCloudImpl;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSimpleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by jock on 15/12/24.
 */
public class JedisCacheDefinitionParser extends AbstractSimpleBeanDefinitionParser
{

    /**
     * element 相当于对应的element元素 parserContext 解析的上下文 builder 用于该标签的实现
     */
    @Override
    protected void doParse(Element element, ParserContext parserContext,
        BeanDefinitionBuilder builder)
    {
        builder.addPropertyValue("cacheClientProxy", createRedisProxyBeanDefinition(element));
    }

    private BeanDefinition createRedisProxyBeanDefinition(Element element)
    {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(RedisCacheCloudImpl.class);
        builder.addPropertyValue("jdRedisClient", createJedisPoolBeanDefinition(element));

        return builder.getBeanDefinition();
    }

    private BeanDefinition createJedisPoolBeanDefinition(Element element)
    {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(JedisPool.class);
        // 从标签中取出对应的属性值
        String host = element.getAttribute("host");
        String port = element.getAttribute("port");
        String password = element.getAttribute("password");
        String timeout = element.getAttribute("timeout");
//        builder.addPropertyValue("host", host);
//        builder.addPropertyValue("port", port);
        builder.setDestroyMethodName("destroy");
        builder.addConstructorArgValue(createJedisPoolConfigBeanDefinition(element));
        builder.addConstructorArgValue(host);
        if (port != null && port.length() > 0)
            builder.addConstructorArgValue(port);
        if (timeout != null && timeout.length() > 0)
            builder.addConstructorArgValue(timeout);
        else
            builder.addConstructorArgValue(3000);
        if (password != null && password.length() > 0)
            builder.addConstructorArgValue(password);

        return builder.getBeanDefinition();
    }

    private BeanDefinition createJedisPoolConfigBeanDefinition(Element element)
    {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(JedisPoolConfig.class);
        // 从标签中取出对应的属性值
        String maxTotal = element.getAttribute("maxTotal");
        String maxIdle = element.getAttribute("maxIdle");
        String minIdle = element.getAttribute("minIdle");
        String testOnBorrow = element.getAttribute("testOnBorrow");
        String testOnReturn = element.getAttribute("testOnReturn");
        String maxWaitMillis = element.getAttribute("timeout");

        if (maxTotal != null && maxTotal.length() > 0)
            builder.addPropertyValue("maxTotal", maxTotal);
        if (maxIdle != null && maxIdle.length() > 0)
            builder.addPropertyValue("maxIdle", maxIdle);
        if (minIdle != null && minIdle.length() > 0)
            builder.addPropertyValue("minIdle", minIdle);
        if (testOnBorrow != null && testOnBorrow.length() > 0)
            builder.addPropertyValue("testOnBorrow", testOnBorrow);
        if (testOnReturn != null && testOnReturn.length() > 0)
            builder.addPropertyValue("testOnReturn", testOnReturn);
        if (maxWaitMillis != null && maxWaitMillis.length() > 0)
            builder.addPropertyValue("maxWaitMillis", maxWaitMillis);

        return builder.getBeanDefinition();
    }

    @Override
    protected Class getBeanClass(Element element)
    {
        // 返回该标签所定义的类实现,在这里是为了创建出CacheProxy对象
        return DefaultCacheImpl.class;
    }

}
