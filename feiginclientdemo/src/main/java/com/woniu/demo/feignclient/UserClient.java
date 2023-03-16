package com.woniu.demo.feignclient;

import com.woniu.demo.entity.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "user")
public interface UserClient {
    @GetMapping("/user/test1")
    R test1(String test1);

    @GetMapping("/user/test2")
    R test2(String test2);
}
