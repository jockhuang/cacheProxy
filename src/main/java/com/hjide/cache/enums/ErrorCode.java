package com.hjide.cache.enums;

/**
 * Created by wanghaitao1 on 2015/11/16.
 */
public enum ErrorCode {

    parameter_error("参数错误.%s"),
    message_error("%s");
    private String cnMsg;

    private ErrorCode(String cnMsg) {
        this.cnMsg = cnMsg;
    }


    public String getCnMsg() {
        return cnMsg;
    }

    public void setCnMsg(String cnMsg) {
        this.cnMsg = cnMsg;
    }
}
