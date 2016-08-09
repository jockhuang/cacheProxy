package com.hjide.lock;

import com.hjide.lock.exception.ConcurrentLockException;
import com.hjide.lock.handle.LockHandler;
import com.hjide.lock.rejected.RejectedLockHandler;

import org.apache.commons.lang.StringUtils;
import java.util.concurrent.TimeUnit;

/**
 * redis并发锁
 * <p/>
 * User: xuxianjun
 * Date: 14-11-20
 * Time: 下午2:34
 * To change this template use File | Settings | File Templates.
 */
public class SimpleConcurrentLock<T> implements ConcurrentLock<T, String> {


    private AcquireLock acquireLock;

    /**
     * 获取锁失败处理方式
     * 默认向调用主抛异常
     */
    private RejectedLockHandler rejectedLockHandler = new RejectedLockHandler.AbortPolicy();


    /**
     * @param lockHandler 获取到锁后的处理器
     * @return
     * @throws ConcurrentLockException
     */
    public T lockHandle(LockHandler<T> lockHandler) throws ConcurrentLockException {
        return lockHandle0(lockHandler, -1, null);
    }


    protected T lockHandle0(LockHandler<T> lockHandler, long timeout, TimeUnit unit)
            throws ConcurrentLockException {
        String key = null;
        if (lockHandler.getLockKey() == null ||
                StringUtils.isBlank(key = lockHandler.getLockKey().toString())) {
            throw new IllegalArgumentException("锁key为空!lock key:" + key);
        }
        AcquireLock.LockResult lockResult = null;
        try {
            if (timeout > 0 && unit != null) {
                lockResult = acquireLock.lock(lockHandler, timeout, unit);
            } else {
                lockResult = acquireLock.lock(lockHandler);
            }

            if (lockResult.isSuccess()) {
                T result = lockHandler.handleInLock();
                return result;
            } else {
                if (rejectedLockHandler != null) {
                    return rejectedLockHandler.rejectedLock(lockHandler, this);
                } else {
                    throw new ConcurrentLockException("获取任务锁失败;");
                }
            }
        } finally {
            if (lockResult != null && lockResult.isSuccess()) {
                acquireLock.unLock(key);
            }
        }
    }

    /**
     * @param lockHandler 获取到锁后的处理器
     * @param timeout
     * @param timeUnit
     * @return
     * @throws ConcurrentLockException
     */
    public T lockHandle(LockHandler<T> lockHandler, long timeout, TimeUnit timeUnit)
            throws ConcurrentLockException {
        return lockHandle0(lockHandler, timeout, timeUnit);
    }


    public AcquireLock getAcquireLock() {
        return acquireLock;
    }

    public void setAcquireLock(AcquireLock acquireLock) {
        this.acquireLock = acquireLock;
    }

    public RejectedLockHandler getRejectedLockHandler() {
        return rejectedLockHandler;
    }

    public void setRejectedLockHandler(RejectedLockHandler rejectedLockHandler) {
        this.rejectedLockHandler = rejectedLockHandler;
    }
}
