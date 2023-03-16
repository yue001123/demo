package com.xiaomage.annotation;

/**
 * @className: Lock
 * @author: xiaomage
 * @date: 2022/12/25
 **/
public @interface Lock {

    /**
     * lock key
     */
    String value();

    /**
     * 锁超时时间，默认5000ms
     */
    long expireTime() default 5000L;

    /**
     * 锁等待时间，默认50ms
     */
    long waitTime() default 50L;

}
