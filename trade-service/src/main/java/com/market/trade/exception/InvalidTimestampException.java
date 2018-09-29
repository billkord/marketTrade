package com.market.trade.exception;

import com.market.trade.model.Message;

public class InvalidTimestampException extends Exception {

    public InvalidTimestampException() {
        super();
    }

    public InvalidTimestampException(String message) {
        super(message);
    }

    public InvalidTimestampException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidTimestampException(Throwable cause) {
        super(cause);
    }

    protected InvalidTimestampException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
