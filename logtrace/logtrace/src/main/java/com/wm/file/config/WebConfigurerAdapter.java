package com.wm.file.config;

import com.wm.file.intecepter.LogInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: 蜗牛
 * @Date: 2023-2-19 10:47
 * @Description:
 */

@Configuration
public class WebConfigurerAdapter implements WebMvcConfigurer {
    @Bean
    public LogInterceptor logInterceptor() {
        return new LogInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor()).addPathPatterns("/**");
        //可以具体制定哪些需要拦截，哪些不拦截，其实也可以使用自定义注解更灵活完成
        //  .excludePathPatterns("/testxx.html");
    }
}