package com.mp.api.common.response;

import java.util.List;

/**
 * {
 *   "data":[{},...],
 *   "code":200,
 *    "msg":"successful call"
 * }
 *
 * Created by panmin on 16-12-23.
 */
public class ListResponse<T> extends BaseResponse{
    private List<T> data;

    public ListResponse() {
    }

    public ListResponse(List<T> data) {
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
