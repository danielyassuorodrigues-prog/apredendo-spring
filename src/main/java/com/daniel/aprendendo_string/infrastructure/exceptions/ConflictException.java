package com.daniel.aprendendo_string.infrastructure.exceptions;

import java.util.ConcurrentModificationException;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }


    public ConflictException(String message, Throwable throwable) {
        super(message);
    }
}
