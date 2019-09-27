package com.cleverlance.airlabs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ApiKeyNotFoundException extends RuntimeException {
    public ApiKeyNotFoundException() {
        super();
    }

    public ApiKeyNotFoundException(String message) {
        super(message);
    }
}