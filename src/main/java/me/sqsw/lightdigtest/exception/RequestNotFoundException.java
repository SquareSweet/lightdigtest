package me.sqsw.lightdigtest.exception;

public class RequestNotFoundException extends RuntimeException {
    public RequestNotFoundException(Long id) {
        super(String.format("Request id '%s' not found", id));
    }
}
