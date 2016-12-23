package com.mp.api.common.response;

/**
 * {
 *   "data":[
 *      12,
 *      ...
 *    ],
 *   "code":200,
 *    "msg":"successful call"
 * }
 *
 * Created by panmin on 16-12-23.
 */
public class ArrayResponse<T> extends BaseResponse{
    private T[] data;

    public ArrayResponse() {
    }

    public ArrayResponse(T[] data) {
        this.data = data;
    }

    public T[] getData() {
        return data;
    }

    public void setData(T[] data) {
        this.data = data;
    }
}
