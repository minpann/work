package com.mp.api.response;

/**
 * Created by panmin on 16-12-23.
 */
public class GenericResponse extends BaseResponse{
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
