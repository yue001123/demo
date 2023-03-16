/*
 * Copyright (C), 2019-2020 dingyong
 * FileName: Recode
 * Author:   dingyong
 * Date:     2020/12/5
 * Description: //描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package org.example.redislimater.utils;

/**
 * @author dingyong
 * @Description: TODO
 * @Date 2020/12/5
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public enum Recode {
    SUCCESS(200,"成功"),
    FAILUR(500,"失败"),
    EMPTY(600,"空"),
    LIMIT_ERROR(100001,"err:100001 访问失败！超过访问限制！"),
    IP_ERROR(100002,"err:100002 IP为空 非法操作"),
    PARAM_ERROR(100004,"err:100004 参数错误"),
    CHOU_JIANG_LIMIT(100003,"抽奖次数不够"),;

    private Integer code;
    private String desc;

    Recode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
