package com.hjide.lock;

import com.hjide.lock.exception.ConcurrentLockException;
import com.hjide.lock.handle.LockHandler;

import java.util.concurrent.TimeUnit;

/**
 * 并发锁
 * <p/>
 */
public interface ConcurrentLock<T, K>
{

    /**
     * 获取锁，并执行
     *
     * @param lockHandler 获取到锁后的处理器
     * @return
     * @throws ConcurrentLockException 表示获取锁失败
     */
    T lockHandle(LockHandler<T> lockHandler)
        throws ConcurrentLockException;

    /**
     * 获取锁，并执行
     *
     * @param lockHandler 获取到锁后的处理器
     * @param timeout
     * @param timeUnit
     * @return
     * @throws ConcurrentLockException 表示获取锁失败
     */
    T lockHandle(LockHandler<T> lockHandler, long timeout, TimeUnit timeUnit)
        throws ConcurrentLockException;

}
