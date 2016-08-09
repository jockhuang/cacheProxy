package com.hjide.lock.handle;

/**
 * 获取到锁后的处理器：handler
 * <p/>
 * User: xuxianjun
 * Date: 14-11-20
 * Time: 下午2:57
 * To change this template use File | Settings | File Templates.
 */
public interface LockHandler<T> extends LockKeyClient {

    /**
     * 在锁中处理方法
     *
     * @return
     */
    public T handleInLock();



}
