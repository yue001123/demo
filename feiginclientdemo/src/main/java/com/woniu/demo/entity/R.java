package com.woniu.demo.entity;

import lombok.Data;

@Data
public class R<T> {

    private Integer code;
    private String message;
    private T data;

}
