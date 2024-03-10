package me.sqsw.lightdigtest.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super(String.format("User '%s' not found", id));
    }
}
