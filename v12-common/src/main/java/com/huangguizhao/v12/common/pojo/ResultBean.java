package com.huangguizhao.v12.common.pojo;

import java.io.Serializable;

/**
 * @author huangguizhao
 */
public class ResultBean<T> implements Serializable{

    //返回的状态码
    private Integer statusCode;

    //成功时返回的数据
    private T data;

    public ResultBean(){}

    public ResultBean(Integer statusCode,T data){
        this.statusCode = statusCode;
        this.data = data;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
