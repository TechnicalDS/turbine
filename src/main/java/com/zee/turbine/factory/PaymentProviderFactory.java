package com.zee.turbine.factory;

import com.zee.turbine.constant.ErrorConstants;
import com.zee.turbine.constant.ProviderName;
import com.zee.turbine.exception.InvalidRequestException;
import com.zee.turbine.factory.AdyenProvider;
import com.zee.turbine.factory.AppleProvider;
import com.zee.turbine.factory.PaymentProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.zee.turbine.constant.ApplicationConstants.ADYEN;
import static com.zee.turbine.constant.ApplicationConstants.APPLE;
import static com.zee.turbine.constant.ErrorConstants.BAD_REQUEST_ERROR_CODE;

/**
 * @author Saba Imteyaz
 * @Date 30/03/2022
 */
@Component
public class PaymentProviderFactory {

    @Autowired
    private AppleProvider appleProvider;

    @Autowired
    private AdyenProvider adyenProvider;

    /**
     *
     * @param providerName
     * @return PaymentProvider instance
     */
    public PaymentProvider getPaymentProvider(String providerName) {
        PaymentProvider paymentProvider = null;
        switch(ProviderName.getProviderName(providerName)) {

            case ADYEN -> paymentProvider = adyenProvider;

            case APPLE -> paymentProvider = appleProvider;
        }
        return paymentProvider;
    }
}
