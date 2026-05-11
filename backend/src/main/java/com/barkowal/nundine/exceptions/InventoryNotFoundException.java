package com.barkowal.nundine.exceptions;

public class InventoryNotFoundException extends RuntimeException {
    public InventoryNotFoundException() {
    }

    public InventoryNotFoundException(String message) {
        super(message);
    }

    public InventoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public InventoryNotFoundException(Throwable cause) {
        super(cause);
    }

    public InventoryNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
