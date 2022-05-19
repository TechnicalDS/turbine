package com.zee.turbine.feign.client;

import com.zee.turbine.facade.payment.dto.CreatePaymentRequest;
import com.zee.turbine.facade.payment.dto.PaymentResponse;
import com.zee.turbine.facade.payment.dto.UpdatePaymentRequest;
import com.zee.turbine.response.ZeePaymentAPIResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.zee.turbine.constant.ApplicationConstants.PAYMENT_ISC_KEY_NAME;

/**
 * @author Saba Imteyaz
 * @Date 14/03/22
 */

/**
 * Feign client to call payment service apis
 */
@FeignClient(value="payment-client", url="${payment.payment-url}")
public interface PaymentClient {


    @RequestMapping(method=RequestMethod.POST)
    ResponseEntity<ZeePaymentAPIResponse<PaymentResponse>> createPayment(@RequestHeader(PAYMENT_ISC_KEY_NAME) String paymentKey,
                                                                        @RequestBody CreatePaymentRequest createPaymentRequest);

    @RequestMapping(method=RequestMethod.PUT)
    ResponseEntity<ZeePaymentAPIResponse<PaymentResponse>> updatePayment(@RequestHeader(PAYMENT_ISC_KEY_NAME) String paymentKey,
                                                  @RequestBody UpdatePaymentRequest updatePaymentRequest);

}
