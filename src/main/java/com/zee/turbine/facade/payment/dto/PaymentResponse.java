package com.zee.turbine.facade.payment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.zee.turbine.constant.PaymentState;
import com.zee.turbine.constant.PaymentType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author Saba Imteyaz
 * @Date 14/03/22
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentResponse {

    private UUID paymentId;
    private UUID orderId;
    private PaymentState paymentState;
    private PaymentType paymentType;
    private String paymentDescription;
    private BigDecimal orderAmount = new BigDecimal(0.0);
    private BigDecimal discount = new BigDecimal(0.0);
    private BigDecimal finalAmount = new BigDecimal(0.0);
    private UUID recurringMandateId;
}
