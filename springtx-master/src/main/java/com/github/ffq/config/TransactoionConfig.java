package com.github.ffq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

/**
 * transactoion配置
 *
 * @author Admin
 * @className: TransactoionConfig
 * @author: 蜗牛
 * @date: 2023/1/7
 */
@Configuration
public class TransactoionConfig {

    @Bean
    public PlatformTransactionManager platformTransactionManager(DataSource dataSource) {
        DataSourceTransactionManager platformTransactionManager = new DataSourceTransactionManager();
        platformTransactionManager.setDataSource(dataSource);
        return platformTransactionManager;
    }




}
