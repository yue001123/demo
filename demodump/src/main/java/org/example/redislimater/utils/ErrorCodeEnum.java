/*
 * Copyright (C), 2019-2020 dingyong
 * FileName: ErrorCodeEnum
 * Author:   dingyong
 * Date:     2020/12/7
 * Description: //描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package org.example.redislimater.utils;

/**
 * @author dingyong
 * @Description: TODO
 * @Date 2020/12/7
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public enum ErrorCodeEnum {
    SYSTOM_ERROR("100001","系统错误"),

    ERROR_200001("200001","接收数据失败"),
    PARAM_ERROR("200002","参数不正确"),

    USER_NOT_EXIST("300001","用户不存在或者密码错误"),
    USER_FORBID_ERROR("300003","账号已被禁用，请与管理员联系"),

    // 系统错误
    UNKNOWN("500","系统内部错误，请联系管理员"),
    PATH_NOT_FOUND("404","路径不存在，请检查路径"),
    NO_AUTH("403","没有权限，请联系管理员"),
    DUPLICATE_KEY("501","数据库中已存在该记录"),
    TOKEN_GENERATOR_ERROR("502","token生成失败"),
    NO_UUID("503","uuid为空"),
    SQL_ILLEGAL("504","sql非法"),

    //用户权限错误
    INVALID_TOKEN("1001","token不合法"),

    //七牛OSS错误
    OSS_CONFIG_ERROR("10050","七牛配置信息错误"),
    OSS_UPLOAD_ERROR("10051","OSSBookNote上传失败"),

    ;

    private String code;
    private String value;


    ErrorCodeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return this.code;
    }

    public String getValue() {
        return this.value;
    }
}
