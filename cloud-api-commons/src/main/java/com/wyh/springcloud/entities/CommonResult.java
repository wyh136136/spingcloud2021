package com.wyh.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    private Integer code;
    private String message;
    private T data;
    /**
     * 查询为空的时候使用的构造器
     * @param code
     * @param message
     */
    public CommonResult(Integer code, String message){
        this(code, message, null);
    }
}