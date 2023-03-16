package com.yomahub.tlog.example.feign.vo;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultInfo<T> {

    private String message;
    private String code;
    private T data;


    public ResultInfo(String message, String code, T data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public static ResultInfo error(String message) {
        return new ResultInfo(message,"502",null);
    }



}
