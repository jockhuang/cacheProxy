package com.hjide.lock;


import com.hjide.lock.exception.ConcurrentLockException;
import com.hjide.lock.handle.LockKeyClient;

import java.util.concurrent.TimeUnit;

/**
 * 获取锁
 * <p/>
 * User: xuxianjun
 * Date: 14-11-20
 * Time: 下午3:14
 * To change this template use File | Settings | File Templates.
 */
public interface AcquireLock<K> {


    /**
     * 获取执行锁
     *
     * @param lockKey
     * @return
     * @throws {@link ConcurrentLockException} 当获取锁中
     */
    LockResult lock(LockKeyClient lockKey) throws ConcurrentLockException;

    /**
     * 获取执行锁
     *
     * @param lockKey
     * @param timeout
     * @param timeUnit
     * @return
     * @throws {@link ConcurrentLockException} 当获取锁中
     */
    LockResult lock(LockKeyClient lockKey, long timeout, TimeUnit timeUnit) throws ConcurrentLockException;

    /**
     * 释放锁
     *
     * @param lockKey
     * @return
     */
    boolean unLock(K lockKey);

    public static class LockResult<R> {

        private boolean success = false;

        private R lockInfo;


        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public R getLockInfo() {
            return lockInfo;
        }

        public void setLockInfo(R lockInfo) {
            this.lockInfo = lockInfo;
        }
    }

}
