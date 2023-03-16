package com.yomahub.tlog.example.feign.controller;


import com.yomahub.tlog.example.feign.aop.RedisLimit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SpringBoot如何用redis限流？
 * 可以这样玩！
 */
@Slf4j
@RestController
@RequestMapping("/limit/redis")
public class LimitRedisController {

    /**
     * 基于Redis AOP限流
     */
    @GetMapping("/test")
    @RedisLimit(key = "redis-limit:test", permitsPerSecond = 2, expire = 1, msg = "当前排队人数较多，请稍后再试！")
    public String test() {
        log.info("限流成功。。。");
        return "ok";
    }

}