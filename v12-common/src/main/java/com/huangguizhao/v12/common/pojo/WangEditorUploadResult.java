package com.huangguizhao.v12.common.pojo;

import java.io.Serializable;

/**
 * @author huangguizhao
 */
public class WangEditorUploadResult implements Serializable{

    private String errno;
    private String[] data;

    public WangEditorUploadResult(){}

    public WangEditorUploadResult(String errno,String[] data){
        this.errno = errno;
        this.data = data;
    }

    public String getErrno() {
        return errno;
    }

    public void setErrno(String errno) {
        this.errno = errno;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

}
