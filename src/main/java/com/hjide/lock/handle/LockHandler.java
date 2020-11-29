package com.hjide.lock.handle;

/**
 * 获取到锁后的处理器：handler
 * <p/>
 */
public interface LockHandler<T> extends LockKeyClient
{

    /**
     * 在锁中处理方法
     *
     * @return
     */
    public T handleInLock();

}
