package com.zee.turbine.service;

import com.zee.turbine.dto.*;
import com.zee.turbine.response.ZeePaymentAPIResponse;
import org.springframework.stereotype.Service;

/**
 * @author Saba Imteyaz
 * @Date 14/03/22
 */

@Service
public interface TurbineService {
    ZeePaymentAPIResponse<InitiatePaymentResponse> initiatePayment(InitiatePaymentRequest initiatePaymentRequest);
    ZeePaymentAPIResponse<CallbackProcessResponse> processCallback(CallbackProcessRequest processPaymentRequest);
    ZeePaymentAPIResponse<S2sProcessResponse> processS2s(S2sProcessRequest s2sProcessRequest);
}
