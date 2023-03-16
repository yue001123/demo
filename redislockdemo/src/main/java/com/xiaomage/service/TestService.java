package com.xiaomage.service;


import com.xiaomage.annotation.Lock;
import com.xiaomage.entity.Student;
import com.xiaomage.vo.LockResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class TestService {

    @Resource
    private LockManager lockManager;


    @Lock(value = "'test_'+#student.stuNo",expireTime = 3000, waitTime = 30)
    public String test(Student student) {
        // 业务代码
        return "";
    }


}
