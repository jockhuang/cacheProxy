package com.hjide.lock.handle;

/**
 * 简单的锁执行方式，
 * 把锁的key通过构造方法传递
 * <p/>
 * Created by xuxianjun on 2015/7/8.
 */
public abstract class SimpleLockHandler<T> implements LockHandler<T>
{

    /**
     * 获取锁key
     *
     * @return
     */
    public abstract String getLockKey();

    public int getExpireSecond()
    {
        return 30;
    }

    /**
     * 校验锁失效时间阈值次数
     *
     * @return
     */
    public long getCheckExpireNum()
    {
        return 10;
    }

}
