package com.hjide.cache.lock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.Before;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: xuxianjun
 * Date: 14-11-18
 * Time: 上午10:30
 * To change this template use File | Settings | File Templates.
 */
public class BaseTest {


    protected Log log = LogFactory.getLog(this.getClass());
    protected static ApplicationContext appContext;

    static String[] configLocations = new String[]{"spring-config.xml"};


    public static void setUp(String[] configLocations) throws Exception {
        try {
            long start = System.currentTimeMillis();
            if (configLocations == null) {
                configLocations = BaseTest.configLocations;
            }
            System.out.println("正在加载配置文件...");

            appContext = new ClassPathXmlApplicationContext(configLocations);

            System.out.println("配置文件加载完毕,耗时：" + (System.currentTimeMillis() - start));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(BaseTest.class.getResource("/"));
    }

    protected boolean setProtected() {
        return false;
    }

    @Before
    public void autoSetBean() {
        appContext.getAutowireCapableBeanFactory().autowireBeanProperties(this, DefaultListableBeanFactory.AUTOWIRE_BY_NAME, false);
    }

    @AfterClass
    public static void tearDown() throws Exception {
    }
}
