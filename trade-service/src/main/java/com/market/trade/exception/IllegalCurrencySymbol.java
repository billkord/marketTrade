package com.market.trade.exception;

public class IllegalCurrencySymbol extends Exception {

    public IllegalCurrencySymbol() {
        super();
    }

    public IllegalCurrencySymbol(String message) {
        super(message);
    }

    public IllegalCurrencySymbol(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalCurrencySymbol(Throwable cause) {
        super(cause);
    }

    protected IllegalCurrencySymbol(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
