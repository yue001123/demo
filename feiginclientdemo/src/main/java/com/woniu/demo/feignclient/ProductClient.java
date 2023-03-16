package com.woniu.demo.feignclient;

import com.woniu.demo.entity.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 手写一个动态Feign，
 * 实现一个“万能”接口调用
 *
 */
@FeignClient(name = "product")
public interface ProductClient {
    @GetMapping("/product/test1")
    R test1(String test1);

    @GetMapping("/product/test2")
    R test2(String test2);
}
