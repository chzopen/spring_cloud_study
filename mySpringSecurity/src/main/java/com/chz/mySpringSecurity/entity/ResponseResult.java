package com.chz.mySpringSecurity.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseResult<T> {

    private Integer code;
    private String msg;
    private T data;
 
    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
 
    public ResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }
 
    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}