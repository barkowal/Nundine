package com.barkowal.nundine.exceptions;

public class AccountBalanceNotFoundException extends RuntimeException {
    public AccountBalanceNotFoundException() {
    }

    public AccountBalanceNotFoundException(String message) {
        super(message);
    }

    public AccountBalanceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountBalanceNotFoundException(Throwable cause) {
        super(cause);
    }

    public AccountBalanceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
