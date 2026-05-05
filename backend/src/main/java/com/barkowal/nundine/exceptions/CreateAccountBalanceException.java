package com.barkowal.nundine.exceptions;

public class CreateAccountBalanceException extends RuntimeException {
    public CreateAccountBalanceException() {
    }

    public CreateAccountBalanceException(String message) {
        super(message);
    }

    public CreateAccountBalanceException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreateAccountBalanceException(Throwable cause) {
        super(cause);
    }

    public CreateAccountBalanceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
