package com.mp.api.common.exception;

public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 887774306101103939L;
    private int code;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
