package com.hjide.cache.spring;

import com.hjide.lock.SimpleConcurrentLock;
import com.hjide.lock.redis.RedisAcquireLock;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSimpleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * Created by xuxianjun on 2016/1/25.
 */
public class LockCacheDefinitionParser extends AbstractSimpleBeanDefinitionParser
{

    /**
     * element 相当于对应的element元素 parserContext 解析的上下文 builder 用于该标签的实现
     */
    @Override
    protected void doParse(Element element, ParserContext parserContext,
        BeanDefinitionBuilder builder)
    {
        builder.addPropertyValue("acquireLock", createRedisCacheCloudBeanDefinition(element));
    }

    private BeanDefinition createRedisCacheCloudBeanDefinition(Element element)
    {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(RedisAcquireLock.class);

        String cacheProxy = element.getAttribute("cacheProxy");
        String checkExpireNum = element.getAttribute("checkExpireNum");
        String expireSeconds = element.getAttribute("expireSeconds");

        builder.addPropertyReference("cacheProxy", cacheProxy);
        builder.addPropertyValue("checkExpireNum", checkExpireNum);
        builder.addPropertyValue("expireSeconds", expireSeconds);
        return builder.getBeanDefinition();
    }

    @Override
    protected Class getBeanClass(Element element)
    {
        // 返回该标签所定义的类实现,在这里是为了创建出redis并发锁控制对象
        return SimpleConcurrentLock.class;
    }

}
