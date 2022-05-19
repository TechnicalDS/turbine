package com.zee.turbine.factory;

import com.zee.turbine.dto.*;
import com.zee.turbine.facade.olm.OrderService;
import com.zee.turbine.facade.olm.dto.PaymentDetail;
import com.zee.turbine.facade.payment.PaymentService;
import com.zee.turbine.facade.payment.dto.CreatePaymentRequest;
import com.zee.turbine.facade.payment.dto.PaymentResponse;
import com.zee.turbine.facade.payment.dto.UpdatePaymentRequest;
import com.zee.turbine.facade.propay.ProPayService;
import com.zee.turbine.facade.propay.dto.ProPayCallbackProcessResponse;
import com.zee.turbine.facade.propay.dto.ProPayInitiatePaymentResponse;
import com.zee.turbine.facade.propay.dto.ProPayS2sProcessResponse;
import com.zee.turbine.response.ZeePaymentAPIResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.zee.turbine.constant.ApplicationConstants.ADYEN_PAYMENT_FAILED;
import static com.zee.turbine.constant.ApplicationConstants.ADYEN_PAYMENT_USING_METHOD;

/**
 * @author Saba Imteyaz
 * @Date 30/03/2022
 */

@Component
@Slf4j
public class AdyenProvider implements PaymentProvider{

    @Autowired
    private ProPayService proPayService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private OrderService orderService;

    @Override
    public ZeePaymentAPIResponse<InitiatePaymentResponse> initiatePayment(InitiatePaymentRequest initiatePaymentRequest) {
        log.debug("Start AdyenProvider initiatePayment method");
        ProPayInitiatePaymentResponse proPayInitiatePaymentResponse = proPayService.initiatePayment(initiatePaymentRequest);

        PaymentResponse paymentResponse = paymentService.createPayment(
                CreatePaymentRequest.toDto(initiatePaymentRequest, proPayInitiatePaymentResponse));

        ZeePaymentAPIResponse<InitiatePaymentResponse> response = new ZeePaymentAPIResponse<InitiatePaymentResponse>();
        response = response.buildSuccessResponse(0, "Payment initiated", InitiatePaymentResponse.toDto(initiatePaymentRequest, proPayInitiatePaymentResponse, paymentResponse));

        log.debug("end AdyenProvider initiatePayment method response: {}", response);
        return response;

    }

    @Override
    public ZeePaymentAPIResponse<CallbackProcessResponse> processCallback(CallbackProcessRequest callbackProcessRequest) {
        log.debug("Start AdyenProvider processCallback method");
        try {
            ProPayCallbackProcessResponse proPayCallbackProcessResponse = proPayService.processCallback(callbackProcessRequest);
            PaymentResponse paymentResponse = paymentService.updatePayment(UpdatePaymentRequest.toDto(proPayCallbackProcessResponse,
                    ADYEN_PAYMENT_USING_METHOD + proPayCallbackProcessResponse.getPaymentType()));

            orderService.updateOrder(PaymentDetail.toDto(paymentResponse));

            ZeePaymentAPIResponse<CallbackProcessResponse> response = new ZeePaymentAPIResponse<CallbackProcessResponse>();
            response = response.buildSuccessResponse(0, "Callback Processed", CallbackProcessResponse.toDto(paymentResponse, proPayCallbackProcessResponse));

            log.debug("end AdyenProvider processCallback method response: {}", response);
            return response;
        } catch (Exception e) {
            log.error("Exception while calling ProPayService for orderId {} => {}", callbackProcessRequest.getOrderId(), e.getMessage());
            PaymentResponse paymentResponse = paymentService.updatePayment(UpdatePaymentRequest.toDto(callbackProcessRequest,
                    ADYEN_PAYMENT_FAILED + e.getMessage()));

            orderService.updateOrder(PaymentDetail.toDto(paymentResponse));
            throw e;
        }
    }

    @Override
    public ZeePaymentAPIResponse<S2sProcessResponse> processS2s(S2sProcessRequest s2sProcessRequest) {
        log.debug("Start AdyenProvider processS2s method");
        ProPayS2sProcessResponse proPayS2sProcessResponse = proPayService.processS2s(s2sProcessRequest);

        ZeePaymentAPIResponse<S2sProcessResponse> response = new ZeePaymentAPIResponse<S2sProcessResponse>();
        response = response.buildSuccessResponse(0, "S2s Processed", S2sProcessResponse.toS2sProcessResponse(proPayS2sProcessResponse));

        log.debug("end AdyenProvider processS2s method response: {}", response);
        return response;
    }
}
