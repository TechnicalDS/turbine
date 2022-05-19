package com.zee.turbine.facade.payment;

import com.zee.turbine.facade.payment.dto.CreatePaymentRequest;
import com.zee.turbine.facade.payment.dto.UpdatePaymentRequest;
import com.zee.turbine.facade.payment.dto.PaymentResponse;
import com.zee.turbine.response.ZeePaymentAPIResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author Saba Imteyaz
 * @Date 31/03/2022
 */
@Service
public interface PaymentService {

    PaymentResponse createPayment(CreatePaymentRequest createPaymentRequest);

    PaymentResponse updatePayment(UpdatePaymentRequest updatePaymentRequest);
}
