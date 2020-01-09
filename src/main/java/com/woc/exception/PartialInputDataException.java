package com.woc.exception;

public class PartialInputDataException extends RuntimeException {
    private Object data;

    public Object getData() {
        return data;
    }

    public PartialInputDataException(Object data) {
        this.data = data;
    }

    public PartialInputDataException(String message) {
        super(message);
    }

    public PartialInputDataException(String message, Object data) {
        super(message);
        this.data = data;
    }


    public PartialInputDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public PartialInputDataException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
