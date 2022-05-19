package com.zee.turbine.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zee.turbine.constant.ErrorConstants;
import com.zee.turbine.dto.*;
import com.zee.turbine.exception.TurbineException;
import com.zee.turbine.facade.olm.OrderService;
import com.zee.turbine.facade.payment.PaymentService;
import com.zee.turbine.facade.propay.ProPayService;
import com.zee.turbine.model.apple.ApplePSPInfo;
import com.zee.turbine.model.apple.AppleReceiptS2SResponse;
import com.zee.turbine.response.ZeePaymentAPIResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.context.SpringBootTest;

import static com.zee.turbine.TestUtil.*;
import static com.zee.turbine.TestUtil.getAppleReceiptS2SResponse;
import static com.zee.turbine.constant.ErrorConstants.INVALID_RESPONSE_ERROR_CODE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

/**
 * @author Saba Imteyaz
 * @Date 18/04/2022
 */
@SpringBootTest
class AppleProviderTest {

    @Mock
    ProPayService proPayService;

    @Mock
    PaymentService paymentService;

    @Mock
    OrderService orderService;
    
    @Mock
    ObjectMapper objectMapper;


    @InjectMocks
    AppleProvider appleProvider;

    @Test
    void initiatePayment() {
        when(paymentService.createPayment(any())).thenReturn(getPaymentResponse());

        ZeePaymentAPIResponse<InitiatePaymentResponse> response = appleProvider.initiatePayment(getInitiatePaymentRequest());
        assertEquals(response.getSuccess(), true);
        assertEquals(response.getData().getPaymentId(), PAYMENT_ID);
    }

    @Test
    void processCallback() throws JSONException {
        when(proPayService.processCallback(any())).thenReturn(getProPayCallbackProcessResponse());
        when(paymentService.updatePayment(any())).thenReturn(getPaymentResponse());

        ZeePaymentAPIResponse<CallbackProcessResponse> response = appleProvider.processCallback(getCallbackProcessRequest());

        assertEquals(response.getSuccess(), true);
        assertEquals(response.getData().getPaymentId(), PAYMENT_ID);

        when(proPayService.processCallback(any())).thenThrow( new TurbineException(ErrorConstants.getCode(INVALID_RESPONSE_ERROR_CODE),
                ErrorConstants.getMessage(INVALID_RESPONSE_ERROR_CODE)));
        assertThrows(Exception.class, () -> {
            appleProvider.processCallback(getCallbackProcessRequest());
        });

        CallbackProcessRequest callbackProcessRequest = getCallbackProcessRequest();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", "Exception occurred");
        callbackProcessRequest.setPSPInfo(jsonObject);
        when(objectMapper.convertValue(any(), eq(ApplePSPInfo.class))).thenReturn(getApplePSPInfo());

        assertThrows(TurbineException.class, () -> {
            appleProvider.processCallback(callbackProcessRequest);
        });


    }

    @Test
    void processS2s() throws JSONException, JsonProcessingException {
        JSONObject s2sTransactionDetails = new JSONObject();
        s2sTransactionDetails.put("notification_type", "renewal");
        when(objectMapper.convertValue(any(), eq(AppleReceiptS2SResponse.class))).thenReturn(getAppleReceiptS2SResponse());
        when(objectMapper.writeValueAsString(any())).thenReturn("{\"notification_type\":\"renewal\"}");

        S2sProcessRequest s2sProcessRequest = getS2sProcessRequest();
        s2sProcessRequest.setS2sPSPInfo(s2sTransactionDetails);
        ZeePaymentAPIResponse<S2sProcessResponse> response = appleProvider.processS2s(s2sProcessRequest);

        assertEquals(response.getSuccess(), true);
        assertEquals(response.getData().getResponseMessage().getString("message"), "Accepted");

        //when(objectMapper.convertValue(any(), eq(AppleReceiptS2SResponse.class))).thenThrow(new JsonProcessingException("Unable to process"){});
    }
}