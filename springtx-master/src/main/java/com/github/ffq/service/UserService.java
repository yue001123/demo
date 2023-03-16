package com.github.ffq.service;

import com.github.ffq.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @Author: ffq
 * @Description:
 * @Date: Create in 9:42 2019/1/5
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    /**
     * spring事务失效的场景
     * 1. 方法内的自调用
     * 2. 方法是private的
     * 3. 方法是final的
     * 4. 单独的线程调用方法
     * 5. 异常被吃掉
     * 6. 类没有被spring管理
     */
    @Autowired
    private UserService userService;
//    public void save() {
////        userService.save2();
//        UserService userService = (UserService) AopContext.currentProxy();
//        userService.save2();
//    }

//    @Transactional
//    public final void save2() {
//        jdbcTemplate.execute("INSERT INTO user (id, name) VALUES (6, 'Jack6')");
//        int i = 1/0;
//    }

//    @Transactional
//    public  void save2() {
//        jdbcTemplate.execute("INSERT INTO user (id, name) VALUES (2, 'Jack6')");
//        new Thread(()->{
//            jdbcTemplate.execute("INSERT INTO user (id, name) VALUES (6, 'Jack6')");
//            int i = 1/0;
//        }).start();
//    }

    @Autowired
    private PlatformTransactionManager transactionManager;

    /**
     * 编程式事务该如何去实现？
     */
//    public void save() {
//        TransactionStatus transaction = transactionManager.getTransaction(new DefaultTransactionDefinition());
//        try {
//            jdbcTemplate.execute("INSERT INTO user (id, name) VALUES (6, 'Jack6')");
//            int i = 1 / 0;
//            transactionManager.commit(transaction);
//        } catch (DataAccessException e) {
//            log.error(e.getMessage());
//            transactionManager.rollback(transaction);
//        }
//    }




    @Autowired
    private TransactionTemplate transactionTemplate;

    public void save(User user) {
        jdbcTemplate.execute("INSERT INTO user (id, name) VALUES (5, 'Jack5')");

        transactionTemplate.execute((status) -> {
            jdbcTemplate.execute("INSERT INTO user (id, name) VALUES (6, 'Jack6')");
            int i = 1 / 0;
            return Boolean.TRUE;
        });

    }


}
