package com.zee.turbine.exception;

import com.zee.turbine.constant.ErrorConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Saba Imteyaz
 * @Date 14/03/22
 */

@RestControllerAdvice
@Slf4j
public class TurbineExceptionHandler {

    @ExceptionHandler(TurbineException.class)
    public final ResponseEntity<Object> turbineExceptionHandler(TurbineException e){
        log.error("Error {}", e);
        return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public final ResponseEntity<Object> unauthorizedExceptionHandler(TurbineException e){
        log.error("Error {}", e);
        return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(StringToUUIDDecoderException.class)
    public final ResponseEntity<Object> stringToUUIDDecoderExceptionHandler(StringToUUIDDecoderException e){
        log.info("error {}", e);
        return new ResponseEntity(e,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public final ResponseEntity<Object> invalidRequestExceptionHandler(InvalidRequestException e){
        return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
    }
}
