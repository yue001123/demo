/*
 * Copyright (C), 2019-2020 dingyong
 * FileName: ApiCall
 * Author:   dingyong
 * Date:     2021/1/22
 * Description: //描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package org.example.redislimater;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author dingyong
 * @Description: 接口调用监控及限流
 * 限制每个ip对每个方法的访问限制，加上时间限制
 * @Date 2021/1/22
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiCall {

    /**
     * 单位时间内能访问多少次，默认不限次
     * @return 调用次数
     */
    long limitCount() default 1000000000;

    /**
     * 多长时间内限制，默认 60
     * @return 时间
     */
    long time() default 60;

    /**
     * 时间类型，默认毫秒
     * @return 时间类型
     */
    TimeUnit timeUnit() default TimeUnit.MILLISECONDS ;
}
