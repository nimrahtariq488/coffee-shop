package com.assessment.coffeeshop.exception;

public class CoffeeShopException extends RuntimeException {

    private final String code;

    public CoffeeShopException(String code, String message) {
        super(message);
        this.code = code;
    }

    public CoffeeShopException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
