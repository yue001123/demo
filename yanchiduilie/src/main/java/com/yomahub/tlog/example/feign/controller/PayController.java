package com.yomahub.tlog.example.feign.controller;


import com.yomahub.tlog.example.feign.service.RedissonDelayQueue;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 我们用Redisson实现一个优雅的延迟队列。
 */
@Slf4j
@RestController
public class PayController {


    @Autowired
    RedissonDelayQueue redissonDelayQueue;



    @GetMapping("/sendMessage")
    public String getToken() {
        redissonDelayQueue.offerTask("hello,world",5);
        return "ok";
    }



}
