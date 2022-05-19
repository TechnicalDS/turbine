package com.zee.turbine.feign.client;

import com.zee.turbine.facade.propay.dto.*;
import com.zee.turbine.response.ZeePaymentAPIResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.zee.turbine.constant.ApplicationConstants.PROPAY_ISC_KEY_NAME;

/**
 * @author Saba Imteyaz
 * @Date 14/03/22
 */

/**
 * Feign client to call propay service apis
 */
@FeignClient(value="propay-client", url="${propay.propay-url}")
public interface ProPayClient {

    @RequestMapping(method=RequestMethod.POST, value="${propay.initiate-path}")
    ResponseEntity<ZeePaymentAPIResponse<ProPayInitiatePaymentResponse>> initiatePayment(@RequestHeader(PROPAY_ISC_KEY_NAME) String proPayKey,
                                                                                         @RequestBody ProPayInitiatePaymentRequest proPayInitiatePaymentRequest);

    @RequestMapping(method=RequestMethod.POST, value="${propay.callback-path}")
    ResponseEntity<ZeePaymentAPIResponse<ProPayCallbackProcessResponse>> processCallback(@RequestHeader(PROPAY_ISC_KEY_NAME) String proPayKey,
                                                                                         @RequestBody ProPayCallbackProcessRequest proPayCallbackProcessRequest);

    @RequestMapping(method=RequestMethod.POST, value="${propay.s2s-path}")
    ResponseEntity<ZeePaymentAPIResponse<ProPayS2sProcessResponse>> processPaymentS2s(@RequestHeader(PROPAY_ISC_KEY_NAME) String proPayKey,
                                                               @RequestBody ProPayS2sProcessRequest proPayS2sProcessRequest);


}
