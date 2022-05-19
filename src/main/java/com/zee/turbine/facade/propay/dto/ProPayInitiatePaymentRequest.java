package com.zee.turbine.facade.propay.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.zee.turbine.constant.OrderType;
import com.zee.turbine.dto.InitiatePaymentRequest;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * @author Saba Imteyaz
 * @Date 14/03/22
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProPayInitiatePaymentRequest {
    private UUID orderId;
    private UUID providerId;
    private String providerName;
    private BigDecimal orderAmount = new BigDecimal(0.0);
    private String txnCurrency;
    private List<String> languages;
    private String shopperReference;
    private String email;
    private Boolean recurring;
    private Boolean oneClick;
    private OrderType orderType;
    private int freeTrial;

    public static ProPayInitiatePaymentRequest toDto(InitiatePaymentRequest initiatePaymentRequest) {
        ProPayInitiatePaymentRequest proPayInitiatePaymentRequest = new ProPayInitiatePaymentRequest();
        proPayInitiatePaymentRequest.setOrderId(initiatePaymentRequest.getOrderId());
        proPayInitiatePaymentRequest.setProviderId(initiatePaymentRequest.getProviderId());
        proPayInitiatePaymentRequest.setProviderName(initiatePaymentRequest.getProviderName());
        proPayInitiatePaymentRequest.setOrderAmount(initiatePaymentRequest.getOrderAmount());
        proPayInitiatePaymentRequest.setTxnCurrency(initiatePaymentRequest.getTxnCurrency());
        proPayInitiatePaymentRequest.setLanguages(initiatePaymentRequest.getLanguages());
        proPayInitiatePaymentRequest.setShopperReference(initiatePaymentRequest.getShopperReference());
        proPayInitiatePaymentRequest.setEmail(initiatePaymentRequest.getEmail());
        proPayInitiatePaymentRequest.setRecurring(initiatePaymentRequest.getRecurring());
        proPayInitiatePaymentRequest.setOneClick(initiatePaymentRequest.getOneClick());
        proPayInitiatePaymentRequest.setOrderType(initiatePaymentRequest.getOrderType());
        proPayInitiatePaymentRequest.setFreeTrial(initiatePaymentRequest.getFreeTrial());
        return proPayInitiatePaymentRequest;
    }
}
