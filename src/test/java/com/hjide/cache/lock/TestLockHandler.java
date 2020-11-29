package com.hjide.cache.lock;


import com.hjide.lock.handle.SimpleLockHandler;

/**
 * Created with IntelliJ IDEA.
 * User: xuxianjun
 * Date: 14-11-27
 * Time: 下午6:59
 * To change this template use File | Settings | File Templates.
 */
public class TestLockHandler extends SimpleLockHandler<String> {

    public String handleInLock() {
        sleep();
        System.out.println(Thread.currentThread().getName()+" handler end ;");
        return "handleInLock" ;
    }

    public String getLockKey() {
        return "TestLockHandler";
    }

    public int getExpireSecond(){
        return 10;
    }

    void sleep(){
        try {

            Thread.sleep(10);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
