package com.zee.turbine.facade.propay;

import com.zee.turbine.constant.ErrorConstants;
import com.zee.turbine.dto.InitiatePaymentRequest;
import com.zee.turbine.exception.TurbineException;
import com.zee.turbine.facade.propay.dto.ProPayCallbackProcessResponse;
import com.zee.turbine.facade.propay.dto.ProPayInitiatePaymentRequest;
import com.zee.turbine.facade.propay.dto.ProPayInitiatePaymentResponse;
import com.zee.turbine.facade.propay.dto.ProPayCallbackProcessRequest;
import com.zee.turbine.facade.propay.dto.ProPayS2sProcessResponse;
import com.zee.turbine.facade.propay.dto.ProPayS2sProcessRequest;
import com.zee.turbine.dto.S2sProcessRequest;
import com.zee.turbine.dto.CallbackProcessRequest;
import com.zee.turbine.feign.client.ProPayClient;
import com.zee.turbine.response.ZeePaymentAPIResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static com.zee.turbine.constant.ErrorConstants.INVALID_RESPONSE_ERROR_CODE;

/**
 * @author Saba Imteyaz
 * @Date 30/03/2022
 */

@Component
@Slf4j
public class ProPayServiceImpl implements ProPayService {

    @Autowired
    private ProPayClient proPayClient;

    @Value("${propay.propay-isc-key}")
    private String proPayIscKey;

    @Override
    public ProPayInitiatePaymentResponse initiatePayment(InitiatePaymentRequest initiatePaymentRequest) {
        log.debug("start calling ProPay Service initiate payment API");
        ResponseEntity<ZeePaymentAPIResponse<ProPayInitiatePaymentResponse>> initiatePaymentResponseDetail = null;
        try{
            initiatePaymentResponseDetail = proPayClient.initiatePayment(proPayIscKey,
                    ProPayInitiatePaymentRequest.toDto(initiatePaymentRequest));
        } catch (Exception e) {
            log.error("Exception while calling ProPay service: {}", e.getMessage());
            throw e;
        }

        if (initiatePaymentResponseDetail == null
                || initiatePaymentResponseDetail.getBody() == null
                || !initiatePaymentResponseDetail.getBody().getSuccess()
                || initiatePaymentResponseDetail.getBody().getData() == null) {
            log.error("ProPay initiatePayment response is not valid: {}", initiatePaymentResponseDetail);
            throw new TurbineException(ErrorConstants.getCode(INVALID_RESPONSE_ERROR_CODE),
                    ErrorConstants.getMessage(INVALID_RESPONSE_ERROR_CODE));
        }
        ProPayInitiatePaymentResponse proPayInitiatePaymentResponse = initiatePaymentResponseDetail.getBody().getData();
        log.debug("end calling ProPay Service initiate payment API proPayInitiatePaymentResponse: {}", proPayInitiatePaymentResponse);
        return proPayInitiatePaymentResponse;
    }

    @Override
    public ProPayCallbackProcessResponse processCallback(CallbackProcessRequest callbackProcessRequest) {
        log.debug("start calling ProPay service process callback API");
        ResponseEntity<ZeePaymentAPIResponse<ProPayCallbackProcessResponse>> callbackProcessResponseDetail = null;
        try{
            callbackProcessResponseDetail = proPayClient.processCallback(proPayIscKey,
                    ProPayCallbackProcessRequest.toDto(callbackProcessRequest));
        } catch (Exception e) {
            log.error("Exception while calling ProPay service: {}", e.getMessage());
            throw e;
        }

        if (callbackProcessResponseDetail == null
                || callbackProcessResponseDetail.getBody() == null
                || !callbackProcessResponseDetail.getBody().getSuccess()
                || callbackProcessResponseDetail.getBody().getData() == null) {
            log.error("ProPay process callback response is not valid: {}", callbackProcessResponseDetail);
            throw new TurbineException(ErrorConstants.getCode(INVALID_RESPONSE_ERROR_CODE),
                    ErrorConstants.getMessage(INVALID_RESPONSE_ERROR_CODE));
        }
        ProPayCallbackProcessResponse proPayCallbackProcessResponse = callbackProcessResponseDetail.getBody().getData();
        log.debug("end calling ProPay Service process callback API proPayInitiatePaymentResponse: {}", proPayCallbackProcessResponse);
        return proPayCallbackProcessResponse;
    }

    @Override
    public ProPayS2sProcessResponse processS2s(S2sProcessRequest s2sProcessRequest) {
        log.debug("start calling ProPay service process s2s API");
        ResponseEntity<ZeePaymentAPIResponse<ProPayS2sProcessResponse>> s2sProcessResponseDetail = null;
        try{
            s2sProcessResponseDetail = proPayClient.processPaymentS2s(proPayIscKey,
                    ProPayS2sProcessRequest.toDto(s2sProcessRequest));
        } catch (Exception e) {
            log.error("Exception while calling ProPay service: {}", e.getMessage());
            throw e;
        }

        if (s2sProcessResponseDetail == null
                || s2sProcessResponseDetail.getBody() == null
                || !s2sProcessResponseDetail.getBody().getSuccess()
                || s2sProcessResponseDetail.getBody().getData() == null) {
            log.error("ProPay process callback response is not valid: {}", s2sProcessResponseDetail);
            throw new TurbineException(ErrorConstants.getCode(INVALID_RESPONSE_ERROR_CODE),
                    ErrorConstants.getMessage(INVALID_RESPONSE_ERROR_CODE));
        }
        ProPayS2sProcessResponse ProPayS2sProcessResponse = s2sProcessResponseDetail.getBody().getData();
        log.debug("end calling ProPay Service s2s callback API proPayInitiatePaymentResponse: {}", ProPayS2sProcessResponse);
        return ProPayS2sProcessResponse;
    }
}