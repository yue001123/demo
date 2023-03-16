package com.yomahub.tlog.example.feign.controller;


import com.yomahub.tlog.example.feign.entity.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public Person test() {
        Person user = new Person();
        user.setRealName("蜗牛");
        user.setPhoneNumber("1685935467874");
        user.setAddress("江苏省南京市....");
        user.setIdCard("4333333333334334333");
        return user;
    }
}