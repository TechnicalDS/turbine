package com.zee.turbine.authorization;

import com.zee.turbine.constant.ErrorConstants;
import com.zee.turbine.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static com.zee.turbine.constant.ApplicationConstants.TURBINE_ISC_KEY_NAME;
import static com.zee.turbine.constant.ErrorConstants.UNAUTHORIZED_ERROR_CODE;

/**
 * @author Saba Imteyaz
 * @Date 11/03/22
 */

/**
 * This class is based on AOP concept to perform isc key validation
 */

@Aspect
@Component
@Slf4j
public class InterServiceCommunicationAuthorization {

    @Value("${turbine.isc-key}")
    private String apiKey;

    /**
     *
     * @param joinPoint
     * @throws Throwable
     * This method will be executed before
     * each controller method to validate the isc key
     */
    @Before("execution(* com.zee.turbine.controller.TurbineController.*(..))")
    public void validateIscKey(JoinPoint joinPoint) throws Throwable{

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes())
                .getRequest();
        String iscKey = request.getHeader(TURBINE_ISC_KEY_NAME);
        log.info("API key from request header: {}",iscKey);
        log.info("Turbine API key: {}", apiKey);

        if ( !iscKey.equals(apiKey)) {
            log.error("Turbine iscKey is invalid or not found");
            throw new UnauthorizedException(ErrorConstants.getCode(UNAUTHORIZED_ERROR_CODE),
                    ErrorConstants.getMessage(UNAUTHORIZED_ERROR_CODE));
        }

    }
}
