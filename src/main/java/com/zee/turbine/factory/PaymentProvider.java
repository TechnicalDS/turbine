package com.zee.turbine.factory;

import com.zee.turbine.dto.*;
import com.zee.turbine.response.ZeePaymentAPIResponse;
import org.springframework.stereotype.Component;

/**
 * @author Saba Imteyaz
 * @Date 30/03/2022
 */

@Component
public interface PaymentProvider {

    ZeePaymentAPIResponse<InitiatePaymentResponse> initiatePayment(InitiatePaymentRequest initiatePaymentRequest);

    ZeePaymentAPIResponse<CallbackProcessResponse> processCallback(CallbackProcessRequest callbackProcessRequest);

    ZeePaymentAPIResponse<S2sProcessResponse> processS2s(S2sProcessRequest s2sProcessRequest);

}
