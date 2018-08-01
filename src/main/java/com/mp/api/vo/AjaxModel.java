package com.mp.api.vo;

/**
 * Created by panmin on 16-12-23.
 */
public class AjaxModel <T>{
    /**
     * 成功
     */
    private static final short SUCCESS = 0;
    /**
     * 失败
     */
    private static final short FAILED = 1;

    private short status = SUCCESS;
    private String msg = "";
    private T result;

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "AjaxModel{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }
}
