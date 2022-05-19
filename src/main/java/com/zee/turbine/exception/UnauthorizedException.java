package com.zee.turbine.exception;

import org.springframework.stereotype.Component;

@Component
public class UnauthorizedException extends RuntimeException {
    private String errorCode;
    private String errorMessage;

    public UnauthorizedException() {
    }

    public UnauthorizedException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public UnauthorizedException(Throwable throwable) {
        super(throwable);
    }
}
