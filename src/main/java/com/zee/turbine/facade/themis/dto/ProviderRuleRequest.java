package com.zee.turbine.facade.themis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.zee.turbine.dto.InitiatePaymentRequest;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Saba Imteyaz
 * @Date 30/03/2022
 */

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProviderRuleRequest {

    private String providerName;

    private String country;

    private BigDecimal amount = new BigDecimal(0.0);

    private String merchantAccount;

    public static ProviderRuleRequest toDto(InitiatePaymentRequest initiatePaymentRequest) {
        ProviderRuleRequest providerRuleRequest = toDto(initiatePaymentRequest.getProviderName());
        providerRuleRequest.setAmount(initiatePaymentRequest.getOrderAmount());
        providerRuleRequest.setCountry(initiatePaymentRequest.getCountryCode());
        return providerRuleRequest;
    }

    public static ProviderRuleRequest toDto(String providerName) {
        ProviderRuleRequest providerRuleRequest = new ProviderRuleRequest();
        providerRuleRequest.setProviderName(providerName);
        return providerRuleRequest;
    }
}
