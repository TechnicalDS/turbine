package com.zee.turbine.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zee.turbine.constant.ErrorConstants;
import com.zee.turbine.dto.CallbackProcessRequest;
import com.zee.turbine.dto.CallbackProcessResponse;
import com.zee.turbine.dto.InitiatePaymentResponse;
import com.zee.turbine.dto.S2sProcessResponse;
import com.zee.turbine.exception.TurbineException;
import com.zee.turbine.facade.olm.OrderService;
import com.zee.turbine.facade.payment.PaymentService;
import com.zee.turbine.facade.propay.ProPayService;
import com.zee.turbine.response.ZeePaymentAPIResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static com.zee.turbine.TestUtil.*;
import static com.zee.turbine.constant.ErrorConstants.INVALID_RESPONSE_ERROR_CODE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Saba Imteyaz
 * @Date 18/04/2022
 */
@SpringBootTest
class AdyenProviderTest {

    @Mock
    ProPayService proPayService;

    @Mock
    PaymentService paymentService;

    @Mock
    OrderService orderService;

    @InjectMocks
    AdyenProvider adyenProvider;

    @Test
    void initiatePayment() {
        when(proPayService.initiatePayment(any())).thenReturn(getProPayInitiatePaymentResponse());
        when(paymentService.createPayment(any())).thenReturn(getPaymentResponse());

        ZeePaymentAPIResponse<InitiatePaymentResponse> response = adyenProvider.initiatePayment(getInitiatePaymentRequest());
        assertEquals(response.getSuccess(), true);
        assertEquals(response.getData().getPaymentId(), PAYMENT_ID);
    }

    @Test
    void processCallback() {
        when(proPayService.processCallback(any())).thenReturn(getProPayCallbackProcessResponse());
        when(paymentService.updatePayment(any())).thenReturn(getPaymentResponse());

        ZeePaymentAPIResponse<CallbackProcessResponse> response = adyenProvider.processCallback(getCallbackProcessRequest());
        assertEquals(response.getSuccess(), true);
        assertEquals(response.getData().getPaymentId(), PAYMENT_ID);

        when(proPayService.processCallback(any())).thenThrow( new TurbineException(ErrorConstants.getCode(INVALID_RESPONSE_ERROR_CODE),
                ErrorConstants.getMessage(INVALID_RESPONSE_ERROR_CODE)));
        assertThrows(Exception.class, () -> {
            adyenProvider.processCallback(getCallbackProcessRequest());
        });
    }

    @Test
    void processS2s() {
        when(proPayService.processS2s(getS2sProcessRequest())).thenReturn(getProPayS2sProcessResponse());

        ZeePaymentAPIResponse<S2sProcessResponse> response = adyenProvider.processS2s(getS2sProcessRequest());
        assertEquals(response.getSuccess(), true);
    }
}