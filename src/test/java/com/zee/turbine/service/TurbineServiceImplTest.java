package com.zee.turbine.service;

import com.zee.turbine.dto.*;
import com.zee.turbine.exception.InvalidRequestException;
import com.zee.turbine.factory.PaymentProviderFactory;
import com.zee.turbine.facade.themis.ThemisService;
import com.zee.turbine.factory.PaymentProvider;
import com.zee.turbine.response.ZeePaymentAPIResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static com.zee.turbine.TestUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Saba Imteyaz
 * @Date 13/04/2022
 */

@SpringBootTest
class TurbineServiceImplTest {

    @InjectMocks
    TurbineServiceImpl turbineService;

    @Mock
    ThemisService themisService;

    @Mock
    PaymentProviderFactory paymentProviderFactory;

    @Mock
    PaymentProvider paymentProvider;

    @Test
    void initiatePayment() {
        when(themisService.getProviderDetail(any())).thenReturn(getProviderRuleResponse());
        when(paymentProviderFactory.getPaymentProvider(any())).thenReturn(paymentProvider);
        when(paymentProvider.initiatePayment(any())).thenReturn(getInitiatePaymentResponse());

        ZeePaymentAPIResponse<InitiatePaymentResponse> response = turbineService.initiatePayment(getInitiatePaymentRequest());

        assertEquals(response.getSuccess(),true);
        assertEquals(response.getData().getPaymentId(), PAYMENT_ID);

        assertThrows(InvalidRequestException.class, () -> {
            InitiatePaymentRequest initiatePaymentRequest = getInitiatePaymentRequest();
            initiatePaymentRequest.setOrderId(null);
            turbineService.initiatePayment(initiatePaymentRequest);
        });

        assertThrows(InvalidRequestException.class, () -> {
            InitiatePaymentRequest initiatePaymentRequest = getInitiatePaymentRequest();
            initiatePaymentRequest.setProviderName(null);
            turbineService.initiatePayment(initiatePaymentRequest);
        });


    }

    @Test
    void processCallback() {
        when(themisService.getProviderDetail(any())).thenReturn(getProviderRuleResponse());
        when(paymentProviderFactory.getPaymentProvider(any())).thenReturn(paymentProvider);
        when(paymentProvider.processCallback(any())).thenReturn(getCallbackProcessResponse());


        assertEquals(turbineService.processCallback(getCallbackProcessRequest()).getSuccess(),true);
        assertEquals(turbineService.processCallback(getCallbackProcessRequest()).getData().getPaymentId(),PAYMENT_ID);
        assertEquals(turbineService.processCallback(getCallbackProcessRequest()).getData().getPaymentState(),PAYMENT_SUCCESS);

        assertThrows(InvalidRequestException.class, () -> {
            CallbackProcessRequest callbackProcessRequest = getCallbackProcessRequest();
            callbackProcessRequest.setOrderId(null);
            turbineService.processCallback(callbackProcessRequest);
        });

        assertThrows(InvalidRequestException.class, () -> {
            CallbackProcessRequest callbackProcessRequest = getCallbackProcessRequest();
            callbackProcessRequest.setProviderName(null);
            turbineService.processCallback(callbackProcessRequest);
        });
    }

    @Test
    void processS2s() {
        when(themisService.getProviderDetail(any())).thenReturn(getProviderRuleResponse());
        when(paymentProviderFactory.getPaymentProvider(any())).thenReturn(paymentProvider);
        when(paymentProvider.processS2s(any())).thenReturn(getS2sProcessResponse());

        assertEquals(turbineService.processS2s(getS2sProcessRequest()).getSuccess(),true);
        assertEquals(turbineService.processS2s(getS2sProcessRequest()).getData().getProviderName(),PROVIDER_NAME);

        assertThrows(InvalidRequestException.class, () -> {
            S2sProcessRequest s2sProcessRequest = getS2sProcessRequest();
            s2sProcessRequest.setProviderName(null);
            turbineService.processS2s(s2sProcessRequest);
        });
    }

}