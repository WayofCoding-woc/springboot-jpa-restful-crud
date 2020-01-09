package com.woc.exception;

public class AddressNotFoundException extends RuntimeException {
    private Integer addressId;

    public AddressNotFoundException(Integer addressId) {
        this.addressId = addressId;
    }

    public AddressNotFoundException(String message) {
        super(message);
    }

    public AddressNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressNotFoundException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return "No such address found for [addressId="+this.addressId+"]";
    }
}
