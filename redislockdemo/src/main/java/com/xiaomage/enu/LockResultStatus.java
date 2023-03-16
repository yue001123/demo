package com.xiaomage.enu;

/**
 * @className: LockResultStatus
 * @author: xiaomage
 * @date: 2022/12/25
 **/
public enum LockResultStatus {

    /**
     * 通信正常，并且加锁成功
     */
    SUCCESS,
    /**
     * 通信正常，但获取锁失败
     */
    FAILURE,
    /**
     * 通信异常和内部异常，锁状态未知
     */
    EXCEPTION;
}
