package com.zee.turbine.exception;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author Saba Imteyaz
 * @Date 11/03/22
 */

@Component
@Data
public class TurbineException extends RuntimeException {

    private String errorCode;
    private String errorMessage;

    public TurbineException() {
    }

    public TurbineException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public TurbineException(Throwable throwable) {
        super(throwable);
    }
}
