/*
 * Copyright (C), 2019-2020 dingyong
 * FileName: ApiCallException
 * Author:   dingyong
 * Date:     2021/1/22
 * Description: //描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package org.example.redislimater.exception;

/**
 * @author dingyong
 * @Description: TODO
 * @Date 2021/1/22
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ApiCallException extends RuntimeException {

    private static final long serialVersionUID = 4721703562003091668L;
    private final Integer errorCode;

    public ApiCallException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}
