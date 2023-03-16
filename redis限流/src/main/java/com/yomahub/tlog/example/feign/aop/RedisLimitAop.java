package com.yomahub.tlog.example.feign.aop;

import com.yomahub.tlog.example.feign.exception.RedisLimitException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Limit AOP
 */
@Slf4j
@Aspect
@Component
public class RedisLimitAop {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Pointcut("@annotation(com.yomahub.tlog.example.feign.aop.RedisLimit)")
    private void check() {

    }

    private DefaultRedisScript<Long> redisScript;

    @PostConstruct
    public void init() {
        redisScript = new DefaultRedisScript<>();
        redisScript.setResultType(Long.class);
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("rateLimiter.lua")));
    }


    @Before("check()")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        //拿到RedisLimit注解，如果存在则说明需要限流
        RedisLimit redisLimit = method.getAnnotation(RedisLimit.class);

        if (redisLimit != null) {
            //获取redis的key
            String key = redisLimit.key();
            String className = method.getDeclaringClass().getName();
            String name = method.getName();

            String limitKey = key + className + method.getName();

            log.info(limitKey);

            if (StringUtils.isEmpty(key)) {
                throw new RedisLimitException("key cannot be null");
            }

            long limit = redisLimit.permitsPerSecond();

            long expire = redisLimit.expire();

            List<String> keys = new ArrayList<>();
            keys.add(key);

            Long count = stringRedisTemplate.execute(redisScript, keys, String.valueOf(limit), String.valueOf(expire));

            log.info("Access try count is {} for key={}", count, key);

            if (count != null && count == 0) {
                log.debug("获取key失败，key为{}", key);
                throw new RedisLimitException(redisLimit.msg());
            }
        }

    }

//    /**
//     * 构建redis lua脚本
//     *
//     * @return
//     */
//    private String buildLuaScript() {
//        StringBuilder luaString = new StringBuilder();
//        luaString.append("local key = KEYS[1]");
//        //获取ARGV内参数Limit
//        luaString.append("\nlocal limit = tonumber(ARGV[1])");
//        //获取key的次数
//        luaString.append("\nlocal curentLimit = tonumber(redis.call('get', key) or '0')");
//        luaString.append("\nif curentLimit + 1 > limit then");
//        luaString.append("\nreturn 0");
//        luaString.append("\nelse");
//        //自增长 1
//        luaString.append("\n redis.call('INCRBY', key, 1)");
//        //设置过期时间
//        luaString.append("\nredis.call('EXPIRE', key, ARGV[2])");
//        luaString.append("\nend");
//        return luaString.toString();
//    }
//


}