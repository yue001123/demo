/*
 * Copyright (C), 2019-2020 dingyong
 * FileName: PGException
 * Author:   dingyong
 * Date:     2020/12/8
 * Description: //描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package org.example.redislimater.exception;

import com.alibaba.fastjson.JSON;

/**
 * @author dingyong
 * @Description: TODO
 * @Date 2020/12/8
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class PGException extends RuntimeException{

    private static final long serialVersionUID = 8368117911051585269L;
    private final String errorCode;

    public PGException(String errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public PGException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public PGException(String errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public PGException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }


    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
