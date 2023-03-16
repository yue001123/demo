package com.github.ffq.controller;

import com.github.ffq.entity.User;
import com.github.ffq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: woniu
 * @Description:
 * @Date: Create in 9:41 2023/1/5
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 添加用户信息
     * @param user
     */
    @GetMapping
    public void save(User user) {
        //保存数据
        userService.save(user);
    }


}
