package com.zee.turbine.controller;

import com.zee.turbine.constant.ApplicationConstants;
import com.zee.turbine.dto.*;
import com.zee.turbine.response.ZeePaymentAPIResponse;
import com.zee.turbine.service.TurbineService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.zee.turbine.constant.ApplicationConstants.*;

/**
 * @author Saba Imteyaz
 * @Date 11/03/22
 */

@Slf4j
@RestController
@RequestMapping("${api-path.v1}")
@SecurityRequirement(name = TURBINE_ISC_KEY_NAME)
public class TurbineController implements TurbineApi {

    @Autowired
    TurbineService turbineService;

    @Autowired
    private final HttpServletRequest request;

    @Autowired
    public TurbineController(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * @param initiatePaymentRequest
     * @return InitiatePaymentResponse
     * This Api will manage prepare APIs of All providers
     * and process it by interacting
     * with other payment related services
     */
    @Override
    @PostMapping("/payment/initiate")
    public ResponseEntity<ZeePaymentAPIResponse<InitiatePaymentResponse>> initiate(InitiatePaymentRequest initiatePaymentRequest) {
        String accept = request.getHeader(ACCEPT);
        if (accept != null && accept.contains(APPLICATION_JSON)) {
            try {
                return new ResponseEntity<ZeePaymentAPIResponse<InitiatePaymentResponse>>(turbineService.initiatePayment(initiatePaymentRequest), HttpStatus.OK);
            } catch (Exception e) {
                log.error("Error occurred in initiatePayment method {}", e);
                return new ResponseEntity<ZeePaymentAPIResponse<InitiatePaymentResponse>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<ZeePaymentAPIResponse<InitiatePaymentResponse>>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * @param processPaymentRequest
     * @return ProcessPaymentResponse
     * This Api will process the callback calls of all the
     * providers and validate the payment
     * by interacting with other services
     */
    @Override
    @PostMapping("/payment/process/callback")
    public ResponseEntity<ZeePaymentAPIResponse<CallbackProcessResponse>> processCallback(CallbackProcessRequest processPaymentRequest) {
        String accept = request.getHeader(ACCEPT);
        if (accept != null && accept.contains(APPLICATION_JSON)) {
            try {
                return new ResponseEntity<ZeePaymentAPIResponse<CallbackProcessResponse>>(turbineService.processCallback(processPaymentRequest), HttpStatus.OK);
            } catch (Exception e) {
                log.error("Error occurred in processPayment method {}", e);
                return new ResponseEntity<ZeePaymentAPIResponse<CallbackProcessResponse>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<ZeePaymentAPIResponse<CallbackProcessResponse>>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * @param s2sProcessRequest
     * @return S2sProcessResponse
     * This API will process the s2s calls
     */
    @Override
    @PostMapping("/payment/process/s2s")
    public ResponseEntity<ZeePaymentAPIResponse<S2sProcessResponse>> processS2s(S2sProcessRequest s2sProcessRequest) {
        String accept = request.getHeader(ACCEPT);
        if (accept != null && accept.contains(APPLICATION_JSON)) {
            try {
                return new ResponseEntity<ZeePaymentAPIResponse<S2sProcessResponse>>(turbineService.processS2s(s2sProcessRequest), HttpStatus.OK);
            } catch (Exception e) {
                log.error("Error occurred in processPayment method {}", e);
                return new ResponseEntity<ZeePaymentAPIResponse<S2sProcessResponse>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<ZeePaymentAPIResponse<S2sProcessResponse>>(HttpStatus.NOT_IMPLEMENTED);
    }
}

