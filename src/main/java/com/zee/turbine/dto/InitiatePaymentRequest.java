package com.zee.turbine.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.zee.turbine.constant.OrderType;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * @author Saba Imteyaz
 * @Date 11/03/22
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Validated
@Data
public class InitiatePaymentRequest {

    private UUID userId;

    @JsonIgnore
    private UUID providerId;

    private String providerName;

    private UUID orderId;

    private String tenantName;

    private BigDecimal discount = new BigDecimal(0.0);

    private BigDecimal orderAmount = new BigDecimal(0.0);

    private String txnCurrency;

    private String countryCode;

    private List<String> languages;

    private String promoCode;

    private String deviceIp;

    private String shopperReference;

    private String email;

    private Boolean recurring;

    private Boolean oneClick;

    private OrderType orderType;

    private int freeTrial;

    private List<Tax> taxes;

}
