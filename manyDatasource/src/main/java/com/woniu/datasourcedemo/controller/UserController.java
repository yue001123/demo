package com.woniu.datasourcedemo.controller;

import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.woniu.datasourcedemo.model.User;
import com.woniu.datasourcedemo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    /**
     *
     * 如何连接多个数据库? 实现spring多数据源配置！
     * @param id
     * @return
     */
    @GetMapping("/v1/user/{id}")
    public User getById1(@PathVariable String id) {
        return userService.getByUserId1(id);
    }

    @GetMapping(value = "/v2/user/{id}")
    public User getById2(@PathVariable String id) {
        return userService.getByUserId2(id);
    }

    @GetMapping("/insertUserAll")
    public void insertUserAll(@RequestParam("name") String name, @RequestParam("age") String age) {
        userService.insertUserAll(name,age);
    }

}
