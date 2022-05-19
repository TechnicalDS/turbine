package com.zee.turbine.facade.payment;

import com.zee.turbine.exception.TurbineException;
import com.zee.turbine.facade.payment.dto.PaymentResponse;
import com.zee.turbine.facade.propay.dto.ProPayInitiatePaymentResponse;
import com.zee.turbine.feign.client.PaymentClient;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.webjars.NotFoundException;

import static com.zee.turbine.TestUtil.*;
import static com.zee.turbine.TestUtil.getInitiatePaymentRequest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Saba Imteyaz
 * @Date 21/04/2022
 */
@SpringBootTest
class PaymentServiceImplTest {

    @Mock
    PaymentClient paymentClient;

    @InjectMocks
    PaymentServiceImpl paymentService;

    @Test
    void createPayment() {
        when(paymentClient.createPayment(any(), any())).thenReturn(null);
        assertThrows(TurbineException.class, () -> {
            paymentService.createPayment(getCreatePaymentRequest());
        });

        when(paymentClient.createPayment(any(), any())).thenReturn(getPaymentResponseDetail());
        PaymentResponse result = paymentService.createPayment(getCreatePaymentRequest());
        assertEquals(ORDER_ID, result.getOrderId());
        assertEquals(PAYMENT_ID, result.getPaymentId());

        when(paymentClient.createPayment(any(), any())).thenThrow(new NotFoundException(""));
        assertThrows(Exception.class, () -> {
            paymentService.createPayment(getCreatePaymentRequest());
        });
    }

    @Test
    void updatePayment() {
        when(paymentClient.updatePayment(any(), any())).thenReturn(null);
        assertThrows(TurbineException.class, () -> {
            paymentService.updatePayment(getUpdatePaymentRequest());
        });

        when(paymentClient.updatePayment(any(), any())).thenReturn(getPaymentResponseDetail());
        PaymentResponse result = paymentService.updatePayment(getUpdatePaymentRequest());
        assertEquals(ORDER_ID, result.getOrderId());
        assertEquals(PAYMENT_ID, result.getPaymentId());
        assertEquals(PAYMENT_SUCCESS, result.getPaymentState());

        when(paymentClient.updatePayment(any(), any())).thenThrow(new NotFoundException(""));
        assertThrows(Exception.class, () -> {
            paymentService.updatePayment(getUpdatePaymentRequest());
        });
    }
}