package com.mp.api.common.response;

import java.util.List;

/**
 *
 *
 * Created by panmin on 16-12-23.
 */
public class ResponseBuilder {
    private static String DEFAULT_SUCCESS_MSG = "OK";

    public static void setDefaultSuccessMsg(String defaultMsg) {
        DEFAULT_SUCCESS_MSG = defaultMsg;
    }

    public static final BaseResponse success() {
        BaseResponse base = new BaseResponse();
        base.setMsg(DEFAULT_SUCCESS_MSG);
        return base;
    }

    public static final BaseResponse success(String msg) {
        BaseResponse base = new BaseResponse();
        base.setMsg(msg);
        return base;
    }

    public static final BaseResponse invalidArgs(String errMsg) {
        BaseResponse base = new BaseResponse();
        base.invalidArgs(errMsg);
        return base;
    }

    public static final BaseResponse internalError(String errMsg) {
        BaseResponse base = new BaseResponse();
        base.internalError(errMsg);
        return base;
    }

    public static final BaseResponse unAuthorizedAccess(String errMsg) {
        BaseResponse base = new BaseResponse();
        base.unAuthorizedAccess(errMsg);
        return base;
    }

    public static final BaseResponse baseResponse(int code, String msg) {
        BaseResponse base = new BaseResponse();
        base.setCode(code);
        base.setMsg(msg);
        return base;
    }

    public static final GenericResponse genericResponse(Object data) {
        GenericResponse response = new GenericResponse();
        response.setData(data);
        response.setMsg(DEFAULT_SUCCESS_MSG);
        return response;
    }

    public static final GenericResponse genericResponse(String msg, Object data) {
        GenericResponse response = new GenericResponse();
        response.setData(data);
        response.setMsg(msg);
        return response;
    }

    public static final GenericResponse genericResponse(int code, String msg, Object data) {
        GenericResponse response = new GenericResponse();
        response.setCode(code);
        response.setMsg(msg);
        response.setData(data);
        return response;
    }

    public static final <T> SimpleResponse<T> simpleResponse(T data) {
        SimpleResponse<T> response = new SimpleResponse<T>();
        response.setData(data);
        response.setMsg(DEFAULT_SUCCESS_MSG);
        return response;
    }

    public static final <T> SimpleResponse<T> simpleResponse(String msg, T data) {
        SimpleResponse<T> response = new SimpleResponse<T>();
        response.setData(data);
        response.setMsg(msg);
        return response;
    }

    public static final <T> SimpleResponse<T> simpleResponse(int code, String msg, T data) {
        SimpleResponse<T> response = new SimpleResponse<T>();
        response.setCode(code);
        response.setMsg(msg);
        response.setData(data);
        return response;
    }

    public static final <T> ListResponse<T> listResponse(List<T> data) {
        ListResponse<T> response = new ListResponse<T>();
        response.setData(data);
        response.setMsg(DEFAULT_SUCCESS_MSG);
        return response;
    }

    public static final <T> ListResponse<T> listResponse(String msg, List<T> data) {
        ListResponse<T> response = new ListResponse<T>();
        response.setMsg(msg);
        response.setData(data);
        return response;
    }

    public static final <T> ListResponse<T> listResponse(int code, String msg, List<T> data) {
        ListResponse<T> response = new ListResponse<T>();
        response.setCode(code);
        response.setMsg(msg);
        response.setData(data);
        return response;
    }

    public static final <T> ArrayResponse<T> arrayResponse(T[] data) {
        ArrayResponse<T> response = new ArrayResponse<T>();
        response.setData(data);
        response.setMsg(DEFAULT_SUCCESS_MSG);
        return response;
    }

    public static final <T> ArrayResponse<T> arrayResponse(String msg, T[] data) {
        ArrayResponse<T> response = new ArrayResponse<T>();
        response.setMsg(msg);
        response.setData(data);
        return response;
    }

    public static final <T> ArrayResponse<T> arrayResponse(int code, String msg, T[] data) {
        ArrayResponse<T> response = new ArrayResponse<T>();
        response.setCode(code);
        response.setMsg(msg);
        response.setData(data);
        return response;
    }
}
