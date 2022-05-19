package com.zee.turbine.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zee.turbine.constant.ErrorConstants;
import com.zee.turbine.dto.CallbackProcessRequest;
import com.zee.turbine.dto.CallbackProcessResponse;
import com.zee.turbine.dto.InitiatePaymentRequest;
import com.zee.turbine.dto.InitiatePaymentResponse;
import com.zee.turbine.dto.S2sProcessRequest;
import com.zee.turbine.dto.S2sProcessResponse;
import com.zee.turbine.dto.AppleS2sRequest;
import com.zee.turbine.exception.TurbineException;
import com.zee.turbine.facade.olm.OrderService;
import com.zee.turbine.facade.olm.dto.PaymentDetail;
import com.zee.turbine.facade.payment.PaymentService;
import com.zee.turbine.facade.payment.dto.CreatePaymentRequest;
import com.zee.turbine.facade.payment.dto.PaymentResponse;
import com.zee.turbine.facade.payment.dto.UpdatePaymentRequest;
import com.zee.turbine.facade.propay.ProPayService;
import com.zee.turbine.facade.propay.dto.ProPayCallbackProcessResponse;
import com.zee.turbine.model.apple.ApplePSPInfo;
import com.zee.turbine.model.apple.AppleReceiptResponse;
import com.zee.turbine.model.apple.AppleReceiptS2SResponse;
import com.zee.turbine.response.ZeePaymentAPIResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import static com.zee.turbine.constant.ErrorConstants.BAD_REQUEST_ERROR_CODE;

/**
 * @author Saba Imteyaz
 * @Date 30/03/2022
 */

@Primary
@Component
@Slf4j
public class AppleProvider implements PaymentProvider{

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ProPayService proPayService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrderService orderService;

    @Override
    public ZeePaymentAPIResponse<InitiatePaymentResponse> initiatePayment(InitiatePaymentRequest initiatePaymentRequest) {
        log.debug("Start AppleProvider initiatePayment method");
        PaymentResponse paymentResponse = paymentService.createPayment(
                CreatePaymentRequest.toDto(initiatePaymentRequest, null));

        InitiatePaymentResponse initiatePaymentResponse = InitiatePaymentResponse.toDto(initiatePaymentRequest, null,
                paymentResponse);

        ZeePaymentAPIResponse<InitiatePaymentResponse> response = new ZeePaymentAPIResponse<InitiatePaymentResponse>();
        response = response.buildSuccessResponse(0, "Payment initiated", initiatePaymentResponse);

        log.debug("end AppleProvider initiatePayment method response: {}", response);
        return response;
    }

    @Override
    public ZeePaymentAPIResponse<CallbackProcessResponse> processCallback(CallbackProcessRequest callbackProcessRequest) {
        log.debug("Start AppleProvider processCallback method");
        ApplePSPInfo applePSPInfo = objectMapper.convertValue(callbackProcessRequest.getPSPInfo(), ApplePSPInfo.class);

        if (applePSPInfo != null && applePSPInfo.getToken() == null) {
            paymentService.updatePayment(UpdatePaymentRequest.toDto(callbackProcessRequest,
                    "Apple In-App purchase failed: " + applePSPInfo.getMessage()));

            log.error("Request is invalid and contains error message, orderId {}, applePSPInfo: {}", callbackProcessRequest.getOrderId(), applePSPInfo);
            throw new TurbineException(ErrorConstants.getCode(BAD_REQUEST_ERROR_CODE), ErrorConstants.getMessage(BAD_REQUEST_ERROR_CODE));
        } else {
            try {
                ProPayCallbackProcessResponse proPayCallbackProcessResponse = proPayService.processCallback(callbackProcessRequest);
                PaymentResponse paymentResponse = paymentService.updatePayment(UpdatePaymentRequest.toDto(proPayCallbackProcessResponse,
                        "Apple In-App purchase"));

                AppleReceiptResponse appleReceiptResponse = objectMapper.convertValue(proPayCallbackProcessResponse.getCallbackPSPResponse(),
                        AppleReceiptResponse.class);

                orderService.updateOrder(PaymentDetail.toDto(paymentResponse));

                ZeePaymentAPIResponse<CallbackProcessResponse> response = new ZeePaymentAPIResponse<CallbackProcessResponse>();
                response = response.buildSuccessResponse(0, "Callback Processed", CallbackProcessResponse.toDto(paymentResponse, proPayCallbackProcessResponse));

                log.debug("end AppleProvider processPayment method response: {}", response);
                return response;
            } catch (Exception e) {
                log.error("Exception while calling ProPayService for orderId {} => {}", callbackProcessRequest.getOrderId(), e.getMessage());
                PaymentResponse paymentResponse = paymentService.updatePayment(UpdatePaymentRequest.toDto(callbackProcessRequest,
                        "Apple In-App purchase failed: " + e.getMessage()));
                orderService.updateOrder(PaymentDetail.toDto(paymentResponse));
                throw e;
            }
        }
    }

    @Override
    public ZeePaymentAPIResponse<S2sProcessResponse> processS2s(S2sProcessRequest s2sProcessRequest) {
        log.debug("Start AppleProvider processS2s method");
        AppleReceiptS2SResponse appleReceiptS2SResponse = objectMapper.convertValue(s2sProcessRequest.getS2sPSPInfo(), AppleReceiptS2SResponse.class);
        AppleS2sRequest paymentAppleS2sRequest = AppleS2sRequest.toDto(appleReceiptS2SResponse, s2sProcessRequest.getS2sPSPInfo().toString());

        JSONObject s2sTransactionDetails = null;
        try {
            s2sTransactionDetails = new JSONObject(objectMapper.writeValueAsString(appleReceiptS2SResponse));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        PaymentResponse paymentResponse = paymentService.updatePayment(UpdatePaymentRequest.toDto(s2sTransactionDetails, paymentAppleS2sRequest.getTransactionId()));

        ZeePaymentAPIResponse<S2sProcessResponse> response = new ZeePaymentAPIResponse<S2sProcessResponse>();
        response = response.buildSuccessResponse(0, "S2s Processed", S2sProcessResponse.toDto(s2sProcessRequest, "Accepted"));

        log.debug("end AppleProvider processS2s method response: {}", response);
        return response;
    }
}