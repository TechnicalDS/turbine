package com.zee.turbine.facade.themis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author Saba Imteyaz
 * @Date 30/03/2022
 */

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProviderRuleResponse {

    private Boolean isValidPayment;

    private String invalidPaymentReason;

    private BigDecimal minimumPaymentAmount = new BigDecimal(0.0);

    private Boolean gracedBillingAllowed;

    private Float gracedBillingDays;

    private UUID providerId;
}
