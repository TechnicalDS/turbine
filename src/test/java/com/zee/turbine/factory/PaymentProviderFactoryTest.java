package com.zee.turbine.factory;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Saba Imteyaz
 * @Date 18/04/2022
 */

@SpringBootTest
class PaymentProviderFactoryTest {

    @InjectMocks
    PaymentProviderFactory paymentProviderFactory;

    @Mock
    AdyenProvider adyenProvider;

    @Mock
    AppleProvider appleProvider;

    private final String APPLE = "APPLE";
    private final String ADYEN = "ADYEN";

    @Test
    void getPaymentProvider() {
        assertEquals(paymentProviderFactory.getPaymentProvider(APPLE).getClass(), appleProvider.getClass());
        assertEquals(paymentProviderFactory.getPaymentProvider(ADYEN).getClass(), adyenProvider.getClass());
    }
}