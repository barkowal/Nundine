package com.barkowal.nundine.exceptions;

public class CreateInventoryException extends RuntimeException {
    public CreateInventoryException() {
    }

    public CreateInventoryException(String message) {
        super(message);
    }

    public CreateInventoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreateInventoryException(Throwable cause) {
        super(cause);
    }

    public CreateInventoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
