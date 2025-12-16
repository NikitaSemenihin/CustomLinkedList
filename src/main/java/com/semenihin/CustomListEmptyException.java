package com.semenihin;

public class CustomListEmptyException extends RuntimeException {
    public CustomListEmptyException(String message) {
        super(message);
    }

    public CustomListEmptyException(Throwable cause) {
        super(cause);
    }
}
