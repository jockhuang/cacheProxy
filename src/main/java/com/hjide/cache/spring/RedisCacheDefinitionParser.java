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
public class RedisCacheDefinitionParser extends AbstractSimpleBeanDefinitionParser {

    /**
     * element 相当于对应的element元素 parserContext 解析的上下文 builder 用于该标签的实现
     */
    @Override
    protected void doParse(Element element, ParserContext parserContext,
                           BeanDefinitionBuilder builder) {
        builder.addPropertyValue("cacheClientProxy", createRedisProxyBeanDefinition(element));
    }

    private BeanDefinition createRedisProxyBeanDefinition(Element element) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(RedisCacheCloudImpl.class);
        builder.addPropertyValue("jdRedisClient", createJedisPoolBeanDefinition(element));

        return builder.getBeanDefinition();
    }

    private BeanDefinition createJedisPoolBeanDefinition(Element element) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(JedisPool.class);
        // 从标签中取出对应的属性值
        String host = element.getAttribute("host");
        String port = element.getAttribute("port");
        String password = element.getAttribute("password");

//        builder.addPropertyValue("host", host);
//        builder.addPropertyValue("port", port);
        builder.setDestroyMethodName("destroy");
        builder.addConstructorArgValue(createJedisPoolConfigBeanDefinition(element));
        builder.addConstructorArgValue(host);
        builder.addConstructorArgValue(port);
        return builder.getBeanDefinition();
    }

    private BeanDefinition createJedisPoolConfigBeanDefinition(Element element) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(JedisPoolConfig.class);
        // 从标签中取出对应的属性值
        String maxTotal = element.getAttribute("maxTotal");
        String maxActive = element.getAttribute("maxActive");
        String maxIdle = element.getAttribute("maxIdle");
        String testOnBorrow = element.getAttribute("testOnBorrow");
        String testOnReturn = element.getAttribute("testOnReturn");
        String maxWaitMillis = element.getAttribute("timeout");


//        builder.addPropertyValue("maxActive", maxActive);
        builder.addPropertyValue("maxIdle", maxIdle);
        builder.addPropertyValue("testOnBorrow",testOnBorrow);
        builder.addPropertyValue("testOnReturn",testOnReturn);
        builder.addPropertyValue("maxWaitMillis",maxWaitMillis);
        builder.addPropertyValue("maxTotal",maxTotal);
        return builder.getBeanDefinition();
    }

    @Override
    protected Class getBeanClass(Element element) {
        // 返回该标签所定义的类实现,在这里是为了创建出CacheProxy对象
        return DefaultCacheImpl.class;
    }

}
