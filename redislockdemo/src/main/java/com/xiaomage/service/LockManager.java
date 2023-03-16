package com.xiaomage.service;


import com.xiaomage.enu.LockResultStatus;
import com.xiaomage.enu.ResponseEnum;
import com.xiaomage.exception.BizException;
import com.xiaomage.vo.LockResult;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * 锁管理器
 *
 * @author xiaomage
 * @date 2022/12/25
 */
@Service
@Slf4j
public class LockManager {

    /**
     * 最小锁等待时间
     */
    private static final int MIN_WAIT_TIME = 10;

    @Resource
    private RedissonClient redisson;

    /**
     * 加锁，加锁失败抛默认异常 - 操作频繁, 请稍后再试
     *
     * @param key        加锁唯一key
     * @param expireTime 锁超时时间 毫秒
     * @param waitTime   加锁最长等待时间 毫秒
     * @return LockResult  加锁结果
     */
    public LockResult lock(String key, long expireTime, long waitTime) {
        return lock(key, expireTime, waitTime, () -> new BizException(ResponseEnum.COMMON_FREQUENT_OPERATION_ERROR));
    }

    /**
     * 加锁，加锁失败抛异常 - 自定义异常
     *
     * @param key               加锁唯一key
     * @param expireTime        锁超时时间 毫秒
     * @param waitTime          加锁最长等待时间 毫秒
     * @param exceptionSupplier 加锁失败时抛该异常，传null时加锁失败不抛异常
     * @return LockResult  加锁结果
     */
    private LockResult lock(String key, long expireTime, long waitTime, Supplier<BizException> exceptionSupplier) {
        if (waitTime < MIN_WAIT_TIME) {
            waitTime = MIN_WAIT_TIME;
        }

        LockResult result = new LockResult();
        try {
            RLock rLock = redisson.getLock(key);
            try {
                if (rLock.tryLock(waitTime, expireTime, TimeUnit.MILLISECONDS)) {
                    result.setLockResultStatus(LockResultStatus.SUCCESS);
                    result.setRLock(rLock);
                } else {
                    result.setLockResultStatus(LockResultStatus.FAILURE);
                }
            } catch (InterruptedException e) {
                log.error("Redis 获取分布式锁失败, key: {}, e: {}", key, e.getMessage());
                result.setLockResultStatus(LockResultStatus.EXCEPTION);
                rLock.unlock();
            }
        } catch (Exception e) {
            log.error("Redis 获取分布式锁失败, key: {}, e: {}", key, e.getMessage());
            result.setLockResultStatus(LockResultStatus.EXCEPTION);
        }

        if (exceptionSupplier != null && LockResultStatus.FAILURE.equals(result.getLockResultStatus())) {
            log.warn("Redis 加锁失败, key: {}", key);
            throw exceptionSupplier.get();
        }

        log.info("Redis 加锁结果：{}, key: {}", result.getLockResultStatus(), key);

        return result;
    }

    /**
     * 解锁
     */
    public void unlock(RLock rLock) {
        try {
            rLock.unlock();
        } catch (Exception e) {
            log.warn("Redis 解锁失败", e);
        }
    }


}
