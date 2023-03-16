package com.yomahub.tlog.example.feign.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 日志文件如何优雅的实现数据脱敏？
 *
 */
@RestController
@Slf4j
public class TestController {

    public final static Logger logger = LoggerFactory.getLogger(TestController.class);


    @Test
    public void test3(){
        logger.info("姓名：{}，电话：{}","蜗牛哥哥","18351905928");
    }
}