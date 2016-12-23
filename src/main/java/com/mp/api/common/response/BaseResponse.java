package com.mp.api.common.response;

/**
 * Created by panmin on 16-12-23.
 */
public class BaseResponse {
    private Integer code = ResponseCode.SUCCESS;
    private String msg;

    public BaseResponse unAuthorizedAccess() {
        this.code = ResponseCode.INVALID_AUTH;
        return this;
    }

    public BaseResponse unAuthorizedAccess(String errMsg) {
        this.code = ResponseCode.INVALID_AUTH;
        this.msg = errMsg;
        return this;
    }

    public BaseResponse invalidArgs() {
        this.code = ResponseCode.INVALID_ARGS;
        return this;
    }

    public BaseResponse invalidArgs(String errMsg) {
        this.code = ResponseCode.INVALID_ARGS;
        this.msg = errMsg;
        return this;
    }

    public BaseResponse internalError() {
        this.code = ResponseCode.INTERNAL_ERROR;
        return this;
    }

    public BaseResponse internalError(String errMsg) {
        this.code = ResponseCode.INTERNAL_ERROR;
        this.msg = errMsg;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
