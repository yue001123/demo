/*
 * Copyright (C), 2019-2020 dingyong
 * FileName: ServiceException
 * Author:   dingyong
 * Date:     2020/12/7
 * Description: //描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package org.example.redislimater.exception;


import org.example.redislimater.utils.ErrorCodeEnum;

/**
 * @author dingyong
 * @Description: TODO
 * @Date 2020/12/7
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ServiceException extends RuntimeException {
    private String code;

    public ServiceException(String code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(String code, Throwable ex) {
        super(ex);
        this.code = code;
    }

    public ServiceException(String code, String message, Throwable ex) {
        super(message, ex);
        this.code = code;
    }

    public ServiceException(ErrorCodeEnum code) {
        super(code.getValue());
        this.code = code.getCode();
    }

    public ServiceException(ErrorCodeEnum code, String message) {
        super(message);
        this.code = code.getCode();
    }

    public ServiceException(ErrorCodeEnum code, Throwable ex) {
        this(code);
    }

    public ServiceException(ErrorCodeEnum code, String message, Throwable ex) {
        super(message, ex);
        this.code = code.getCode();
    }

    public String getCode() {
        return this.code;
    }
}
