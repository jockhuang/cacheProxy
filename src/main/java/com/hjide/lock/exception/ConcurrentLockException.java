package com.hjide.lock.exception;

/**
 * 获取锁失败后，抛出异常
 *
 * User: xuxianjun
 * Date: 14-11-20
 * Time: 下午3:16
 * To change this template use File | Settings | File Templates.
 */
public class ConcurrentLockException extends Exception {

    private static final long serialVersionUID = -375805702767069545L;



    public ConcurrentLockException() { }


    public ConcurrentLockException(String message) {
        super(message);
    }

    public ConcurrentLockException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConcurrentLockException(Throwable cause) {
        super(cause);
    }
}
