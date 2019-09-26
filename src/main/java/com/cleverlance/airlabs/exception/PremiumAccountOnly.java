package com.cleverlance.airlabs.exception;

public class PremiumAccountOnly extends RuntimeException {
    public PremiumAccountOnly() {
        super();
    }

    public PremiumAccountOnly(String message) {
        super(message);
    }
}
