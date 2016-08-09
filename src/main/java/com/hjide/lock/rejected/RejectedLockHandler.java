package com.hjide.lock.rejected;


import com.hjide.lock.redis.RedisAcquireLock;
import com.hjide.lock.ConcurrentLock;
import com.hjide.lock.exception.ConcurrentLockException;
import com.hjide.lock.handle.LockHandler;

/**
 * 获取锁失败处理机制
 * <p/>
 * 用于获取锁失败后，
 * <p/>
 * User: xuxianjun
 * Date: 14-11-20
 * Time: 下午4:11
 * To change this template use File | Settings | File Templates.
 */
public interface RejectedLockHandler {

    <T> T rejectedLock(LockHandler lockHandler, ConcurrentLock concurrentLock)
            throws ConcurrentLockException;


    /**
     * 获取锁失败后，抛出异常
     */
    public class AbortPolicy implements RejectedLockHandler {

        public <T> T rejectedLock(LockHandler lockHandler, ConcurrentLock concurrentLock)
                throws ConcurrentLockException {
            throw new ConcurrentLockException(lockHandler.getLockKey() + "获取锁失败!");
        }
    }

    /**
     * 不获取锁，直接执行，不建议配置使用
     * 但可以与{@link RedisAcquireLock}的超时方法结合使用
     * 如等待一定时间后，仍获取不到锁，在一些场景是可以直接忽略锁，直接执行的！
     */
    public class ExecutePolicy implements RejectedLockHandler {


        public <T> T rejectedLock(LockHandler lockHandler, ConcurrentLock concurrentLock) {
            return (T) lockHandler.handleInLock();
        }
    }

    /**
     * 忽略请求，返回空
     */
    public class DiscardPolicy implements RejectedLockHandler {

        public <T> T rejectedLock(LockHandler lockHandler, ConcurrentLock concurrentLock) {
            return null;
        }
    }
}
