package com.zee.turbine.facade.payment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.zee.turbine.dto.InitiatePaymentRequest;
import com.zee.turbine.dto.Tax;
import com.zee.turbine.facade.propay.dto.ProPayInitiatePaymentResponse;
import lombok.Data;
import org.json.JSONObject;

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
public class CreatePaymentRequest {

    private UUID userId;
    private UUID providerId;
    private String providerName;
    private UUID orderId;
    private BigDecimal orderAmount;
    private BigDecimal discount;
    private BigDecimal finalAmount;
    private List<Tax> taxes;
    private String txnCurrency;
    private String countryCode;
    private String promoCode;
    private String deviceIp;
    private JsonNode transactionDetails;

    public static CreatePaymentRequest toDto(InitiatePaymentRequest initiatePaymentRequest, ProPayInitiatePaymentResponse proPayInitiatePaymentResponse) {
        CreatePaymentRequest createPaymentRequest = new CreatePaymentRequest();
        createPaymentRequest.setUserId(initiatePaymentRequest.getUserId());
        createPaymentRequest.setProviderId(initiatePaymentRequest.getProviderId());
        createPaymentRequest.setProviderName(initiatePaymentRequest.getProviderName());
        createPaymentRequest.setOrderId(initiatePaymentRequest.getOrderId());
        createPaymentRequest.setOrderAmount(initiatePaymentRequest.getOrderAmount());
        createPaymentRequest.setDiscount(initiatePaymentRequest.getDiscount());
        createPaymentRequest.setTaxes(initiatePaymentRequest.getTaxes());
        createPaymentRequest.setTxnCurrency(initiatePaymentRequest.getTxnCurrency());
        createPaymentRequest.setCountryCode(initiatePaymentRequest.getCountryCode());
        createPaymentRequest.setPromoCode(initiatePaymentRequest.getPromoCode());
        createPaymentRequest.setDeviceIp(initiatePaymentRequest.getDeviceIp());
        if (proPayInitiatePaymentResponse != null
                && proPayInitiatePaymentResponse.getPsPInitiateResponse() != null)
            createPaymentRequest.setTransactionDetails(proPayInitiatePaymentResponse.getPsPInitiateResponse());
        return createPaymentRequest;
    }
}
