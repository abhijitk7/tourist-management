package com.cts.fse.exceptions;

public class AggregateNotFoundException extends RuntimeException {
    public AggregateNotFoundException(String message) {
        super(message);
    }
}
