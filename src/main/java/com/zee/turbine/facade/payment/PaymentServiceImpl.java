package com.zee.turbine.facade.payment;

import com.zee.turbine.constant.ErrorConstants;
import com.zee.turbine.exception.TurbineException;
import com.zee.turbine.facade.payment.dto.CreatePaymentRequest;
import com.zee.turbine.facade.payment.dto.PaymentResponse;
import com.zee.turbine.facade.payment.dto.UpdatePaymentRequest;
import com.zee.turbine.feign.client.PaymentClient;
import com.zee.turbine.response.ZeePaymentAPIResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static com.zee.turbine.constant.ErrorConstants.*;

/**
 * @author Saba Imteyaz
 * @Date 31/03/2022
 */
@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentClient paymentClient;


    @Value("${payment.payment-isc-key}")
    private String paymentIscKey;

    @Override
    public PaymentResponse createPayment(CreatePaymentRequest createPaymentRequest) {
        log.debug("start calling Payment Service create payment API");
        ResponseEntity<ZeePaymentAPIResponse<PaymentResponse>> paymentResponseDetail = null;
        try{
            paymentResponseDetail = paymentClient.createPayment(paymentIscKey,
                    createPaymentRequest);
        } catch (Exception e) {
            log.error("Exception while calling Payment service: {}", e.getMessage());
            throw e;
            //throw new TurbineException(ErrorConstants.getCode(PAYMENT_INTERNAL_SERVER_ERROR_CODE),
                    //ErrorConstants.getMessage(PAYMENT_INTERNAL_SERVER_ERROR_CODE));
        }

        if (paymentResponseDetail == null
                || paymentResponseDetail.getBody() == null
                || !paymentResponseDetail.getBody().getSuccess()
                || paymentResponseDetail.getBody().getData() == null) {
            log.error("Payment createPayment response is not valid: {}", paymentResponseDetail);
            throw new TurbineException(ErrorConstants.getCode(INVALID_RESPONSE_ERROR_CODE),
                    ErrorConstants.getMessage(INVALID_RESPONSE_ERROR_CODE));
        }
        PaymentResponse paymentResponse = paymentResponseDetail.getBody().getData();
        log.debug("end calling Payment Service create payment API paymentResponse: {}", paymentResponse);
        return paymentResponse;
    }

    @Override
    public PaymentResponse updatePayment(UpdatePaymentRequest updatePaymentRequest) {
        log.debug("start calling Payment Service update payment API");
        ResponseEntity<ZeePaymentAPIResponse<PaymentResponse>> paymentResponseDetail = null;
        try{
            paymentResponseDetail = paymentClient.updatePayment(paymentIscKey,
                    updatePaymentRequest);
        } catch (Exception e) {
            log.error("Exception while calling Payment service: {}", e.getMessage());
            throw new TurbineException(ErrorConstants.getCode(PAYMENT_INTERNAL_SERVER_ERROR_CODE),
                    ErrorConstants.getMessage(PAYMENT_INTERNAL_SERVER_ERROR_CODE));
        }

        if (paymentResponseDetail == null
                || paymentResponseDetail.getBody() == null
                || !paymentResponseDetail.getBody().getSuccess()
                || paymentResponseDetail.getBody().getData() == null) {
            log.error("Payment updatePayment response is not valid: {}", paymentResponseDetail);
            throw new TurbineException(ErrorConstants.getCode(INVALID_RESPONSE_ERROR_CODE),
                    ErrorConstants.getMessage(INVALID_RESPONSE_ERROR_CODE));
        }
        PaymentResponse paymentResponse = paymentResponseDetail.getBody().getData();
        log.debug("end calling Payment Service update payment API paymentResponse: {}", paymentResponse);
        return paymentResponse;
    }

}