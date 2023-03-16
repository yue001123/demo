package com.woniu.demo.test;

import cn.hutool.extra.spring.SpringUtil;
import com.woniu.demo.feign.DynamicClient;

import java.util.HashMap;


public class DemoC {
    public static void main(String[] args) {
        DynamicClient dynamicClient = SpringUtil.getBean(DynamicClient.class);
        Object user = dynamicClient.executePostApi("user", "/system/test", new HashMap<>());

    }
}
