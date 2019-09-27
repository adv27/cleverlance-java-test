package com.cleverlance.airlabs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PremiumAccountOnlyException extends RuntimeException {
    public PremiumAccountOnlyException() {
        super();
    }

    public PremiumAccountOnlyException(String message) {
        super(message);
    }
}
