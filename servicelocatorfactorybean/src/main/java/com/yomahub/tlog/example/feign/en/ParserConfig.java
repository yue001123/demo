package com.yomahub.tlog.example.feign.en;


import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParserConfig {

    @Bean("parserFactory")
    public FactoryBean serviceLocatorFactoryBean() {
        ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
        // 设置服务定位接口
        factoryBean.setServiceLocatorInterface(ParserFactory.class);
        return factoryBean;
    }

}