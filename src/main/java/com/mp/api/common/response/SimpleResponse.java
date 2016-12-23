package com.mp.api.common.response;

/**
 * {
 *   "data":T,
 *   "code":200,
 *    "msg":"successful call"
 * }
 *
 * Created by panmin on 16-12-23.
 */
public class SimpleResponse <T> extends BaseResponse{
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
