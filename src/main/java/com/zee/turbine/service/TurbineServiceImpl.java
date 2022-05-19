package com.zee.turbine.service;

import com.zee.turbine.constant.ErrorConstants;
import com.zee.turbine.exception.InvalidRequestException;
import com.zee.turbine.factory.PaymentProviderFactory;
import com.zee.turbine.factory.PaymentProvider;
import com.zee.turbine.dto.*;
import com.zee.turbine.response.ZeePaymentAPIResponse;
import com.zee.turbine.facade.themis.ThemisService;
import com.zee.turbine.facade.themis.dto.ProviderRuleRequest;
import com.zee.turbine.facade.themis.dto.ProviderRuleResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.zee.turbine.constant.ErrorConstants.BAD_REQUEST_ERROR_CODE;

/**
 * @author Saba Imteyaz
 * @Date 14/03/22
 */

/**
 * This is a service class that holds the logic for
 * interacting with themis, propay and payment services
 * in order to validate, process and save the payment
 * and get the response
 */
@Service
@Slf4j
public class TurbineServiceImpl implements TurbineService{

    @Autowired
    private ThemisService themisService;

    @Autowired
    private PaymentProvider paymentProvider;

    @Autowired
    private PaymentProviderFactory paymentProviderFactory;

    @Override
    public ZeePaymentAPIResponse<InitiatePaymentResponse> initiatePayment(InitiatePaymentRequest initiatePaymentRequest) {
        log.debug("start initiatePayment initiatePaymentRequest: {}", initiatePaymentRequest);

        if(StringUtils.isEmpty(initiatePaymentRequest.getProviderName())) {
            log.error("providerName not found");
            throw new InvalidRequestException(ErrorConstants.getCode(BAD_REQUEST_ERROR_CODE),
                    ErrorConstants.getMessage(BAD_REQUEST_ERROR_CODE));
        }

        if(initiatePaymentRequest.getOrderId() == null) {
            log.error("orderId not found");
            throw new InvalidRequestException(ErrorConstants.getCode(BAD_REQUEST_ERROR_CODE),
                    ErrorConstants.getMessage(BAD_REQUEST_ERROR_CODE));
        }

        //////////////////////// Rule Validation ////////////////////////
        ProviderRuleResponse providerRuleResponse = themisService.getProviderDetail(ProviderRuleRequest.toDto(initiatePaymentRequest));
        initiatePaymentRequest.setProviderId(providerRuleResponse.getProviderId());

        log.debug("start calling factory method to get paymentProvider instance");
        paymentProvider = paymentProviderFactory.getPaymentProvider(initiatePaymentRequest.getProviderName());

        return paymentProvider.initiatePayment(initiatePaymentRequest);
    }

    @Override
    public ZeePaymentAPIResponse<CallbackProcessResponse> processCallback(CallbackProcessRequest callbackProcessRequest) {
        log.debug("start processCallback callbackProcessRequest: {}", callbackProcessRequest);

        if(StringUtils.isEmpty(callbackProcessRequest.getProviderName())) {
            log.error("providerName not found");
            throw new InvalidRequestException(ErrorConstants.getCode(BAD_REQUEST_ERROR_CODE),
                    ErrorConstants.getMessage(BAD_REQUEST_ERROR_CODE));
        }

        if(callbackProcessRequest.getOrderId() == null) {
            log.error("orderId not found");
            throw new InvalidRequestException(ErrorConstants.getCode(BAD_REQUEST_ERROR_CODE),
                    ErrorConstants.getMessage(BAD_REQUEST_ERROR_CODE));
        }

        //////////////////////// Rule Validation ////////////////////////
        ProviderRuleResponse providerRuleResponse = themisService.getProviderDetail(ProviderRuleRequest.toDto(callbackProcessRequest.getProviderName()));
        callbackProcessRequest.setProviderId(providerRuleResponse.getProviderId());


        log.debug("start calling factory method to get paymentProvider instance");
        paymentProvider = paymentProviderFactory.getPaymentProvider(callbackProcessRequest.getProviderName());

        ZeePaymentAPIResponse<CallbackProcessResponse> response =  paymentProvider.processCallback(callbackProcessRequest);
        return response;
    }

    @Override
    public ZeePaymentAPIResponse<S2sProcessResponse> processS2s(S2sProcessRequest s2sProcessRequest) {
        log.debug("start processS2s s2sProcessRequest: {}", s2sProcessRequest);

        if(StringUtils.isEmpty(s2sProcessRequest.getProviderName())) {
            log.error("providerName not found");
            throw new InvalidRequestException(ErrorConstants.getCode(BAD_REQUEST_ERROR_CODE),
                    ErrorConstants.getMessage(BAD_REQUEST_ERROR_CODE));
        }

        //////////////////////// Rule Validation ////////////////////////
        ProviderRuleResponse providerRuleResponse = themisService.getProviderDetail(ProviderRuleRequest.toDto(s2sProcessRequest.getProviderName()));
        s2sProcessRequest.setProviderId(providerRuleResponse.getProviderId());

        log.debug("start calling factory method to get paymentProvider instance");
        paymentProvider = paymentProviderFactory.getPaymentProvider(s2sProcessRequest.getProviderName());

        return paymentProvider.processS2s(s2sProcessRequest);
    }
}
