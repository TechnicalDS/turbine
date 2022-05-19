package com.zee.turbine.facade.propay;

import com.zee.turbine.dto.*;
import com.zee.turbine.facade.propay.dto.ProPayCallbackProcessResponse;
import com.zee.turbine.facade.propay.dto.ProPayInitiatePaymentResponse;
import com.zee.turbine.facade.propay.dto.ProPayS2sProcessResponse;
import com.zee.turbine.response.ZeePaymentAPIResponse;
import org.springframework.stereotype.Component;

/**
 * @author Saba Imteyaz
 * @Date 30/03/2022
 */

@Component
public interface ProPayService {

    ProPayInitiatePaymentResponse initiatePayment(InitiatePaymentRequest initiatePaymentRequest);

    ProPayCallbackProcessResponse processCallback(CallbackProcessRequest callbackProcessRequest);

    ProPayS2sProcessResponse processS2s(S2sProcessRequest s2sProcessRequest);
}
