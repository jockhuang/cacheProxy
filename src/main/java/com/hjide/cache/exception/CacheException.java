package com.hjide.cache.exception;

import com.hjide.cache.enums.ErrorCode;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;

/**
 * Created by xuxianjun on 2016/1/12.
 */
public class CacheException extends RuntimeException {

    private String code;
    private String cnMsg;

    public CacheException(ErrorCode errorCode, Object... obj) {
        this(errorCode, null, obj);
    }

    public CacheException(ErrorCode errorCode, Throwable e, Object... obj) {
        super(Arrays.toString(obj),e);
        setCode(errorCode.name());
        if (obj == null || obj.length == 0) {
            setCnMsg(errorCode.getCnMsg().replace("%s", ""));
            return;
        }
        if(StringUtils.isNotBlank(errorCode.getCnMsg())){
            try {
                setCnMsg(String.format(errorCode.getCnMsg(), obj));
            }catch (Exception ee){
                setCnMsg(errorCode.getCnMsg());
            }

        }else{
            setCnMsg(Arrays.toString(obj));
        }
    }

    public CacheException(String errorCode, String msg) {
        this(errorCode, null, msg);
    }

    public CacheException(String errorCode, Throwable e, String msg) {
        super(msg,e);
        this.code = errorCode;
        this.cnMsg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCnMsg() {
        return cnMsg;
    }

    public void setCnMsg(String cnMsg) {
        this.cnMsg = cnMsg;
    }

}
