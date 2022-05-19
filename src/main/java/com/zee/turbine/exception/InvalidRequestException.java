package com.zee.turbine.exception;

import org.springframework.stereotype.Component;

/**
 * @author Saba Imteyaz
 * @Date 30/03/22
 */
@Component
public class InvalidRequestException extends RuntimeException{
    private String errorCode;
    private String errorMessage;

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public InvalidRequestException() {
    }

    public InvalidRequestException(String errorCode, String errorMessage){
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage =  errorMessage;
    }

    /**
     * Constructs a new runtime exception with the specified cause and a
     * detail message of {@code (cause==null ? null : cause.toString())}
     * (which typically contains the class and detail message of
     * {@code cause}).  This constructor is useful for runtime exceptions
     * that are little more than wrappers for other throwables.
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method).  (A {@code null} value is
     *              permitted, and indicates that the cause is nonexistent or
     *              unknown.)
     * @since 1.4
     */
    public InvalidRequestException(Throwable cause) {
        super(cause);
    }
}
