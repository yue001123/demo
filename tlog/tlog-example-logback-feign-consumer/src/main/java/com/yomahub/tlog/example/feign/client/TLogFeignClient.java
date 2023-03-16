package com.yomahub.tlog.example.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("tlog-logback-feign-provider")
public interface TLogFeignClient {
    @RequestMapping(value = "hi",method = RequestMethod.GET)
    public String sayHello(@RequestParam(value = "name") String name);
}
