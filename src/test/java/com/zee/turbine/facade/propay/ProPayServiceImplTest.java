package com.zee.turbine.facade.propay;

import com.zee.turbine.exception.TurbineException;
import com.zee.turbine.facade.propay.dto.ProPayCallbackProcessResponse;
import com.zee.turbine.facade.propay.dto.ProPayInitiatePaymentResponse;
import com.zee.turbine.facade.propay.dto.ProPayS2sProcessResponse;
import com.zee.turbine.feign.client.ProPayClient;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.webjars.NotFoundException;

import static com.zee.turbine.TestUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Saba Imteyaz
 * @Date 21/04/2022
 */
@SpringBootTest
class ProPayServiceImplTest {

    @Mock
    ProPayClient proPayClient;

    @InjectMocks
    ProPayServiceImpl proPayService;

    @Test
    void initiatePayment() {
        when(proPayClient.initiatePayment(any(), any())).thenReturn(null);
        assertThrows(TurbineException.class, () -> {
            proPayService.initiatePayment(getInitiatePaymentRequest());
        });

        when(proPayClient.initiatePayment(any(), any())).thenReturn(getProPayInitiatePaymentResponseDetail());
        ProPayInitiatePaymentResponse result = proPayService.initiatePayment(getInitiatePaymentRequest());
        assertEquals(ORDER_ID, result.getOrderId());

        when(proPayClient.initiatePayment(any(), any())).thenThrow(new NotFoundException(""));
        assertThrows(Exception.class, () -> {
            proPayService.initiatePayment(getInitiatePaymentRequest());
        });
    }

    @Test
    void processCallback() {
        when(proPayClient.processCallback(any(), any())).thenReturn(null);
        assertThrows(TurbineException.class, () -> {
            proPayService.processCallback(getCallbackProcessRequest());
        });

        when(proPayClient.processCallback(any(), any())).thenReturn(getProPayCallbackProcessResponseDetail());
        ProPayCallbackProcessResponse result = proPayService.processCallback(getCallbackProcessRequest());
        assertEquals(ORDER_ID, result.getOrderId());
        assertEquals(PAYMENT_SUCCESS, result.getPaymentState());

        when(proPayClient.processCallback(any(), any())).thenThrow(new NotFoundException(""));
        assertThrows(Exception.class, () -> {
            proPayService.processCallback(getCallbackProcessRequest());
        });
    }

    @Test
    void processS2s() {
        when(proPayClient.processPaymentS2s(any(), any())).thenReturn(null);
        assertThrows(TurbineException.class, () -> {
            proPayService.processS2s(getS2sProcessRequest());
        });

        when(proPayClient.processPaymentS2s(any(), any())).thenReturn(getProPayS2sProcessResponseDetail());
        ProPayS2sProcessResponse result = proPayService.processS2s(getS2sProcessRequest());
        assertEquals(PROVIDER_ID, result.getProviderId());

        when(proPayClient.processPaymentS2s(any(), any())).thenThrow(new NotFoundException(""));
        assertThrows(Exception.class, () -> {
            proPayService.processS2s(getS2sProcessRequest());
        });
    }
}