package com.zee.turbine.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.zee.turbine.facade.payment.dto.PaymentResponse;
import com.zee.turbine.facade.propay.dto.ProPayInitiatePaymentResponse;
import lombok.Data;
import org.json.JSONObject;

import java.util.UUID;

/**
 * @author Saba Imteyaz
 * @Date 11/03/22
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class InitiatePaymentResponse {

    private UUID orderId;

    private UUID paymentId;

    private JsonNode pSPResponse;

    public static InitiatePaymentResponse toDto(InitiatePaymentRequest initiatePaymentRequest, PaymentResponse paymentResponse) {
        InitiatePaymentResponse initiatePaymentResponse = new InitiatePaymentResponse();
        initiatePaymentResponse.setPaymentId(paymentResponse.getPaymentId());
        initiatePaymentResponse.setOrderId(initiatePaymentRequest.getOrderId());
        return initiatePaymentResponse;
    }

    public static InitiatePaymentResponse toDto(InitiatePaymentRequest initiatePaymentRequest, ProPayInitiatePaymentResponse proPayInitiatePaymentResponse, PaymentResponse paymentResponse) {
        InitiatePaymentResponse initiatePaymentResponse = toDto(initiatePaymentRequest, paymentResponse);
        if( proPayInitiatePaymentResponse != null) {
            initiatePaymentResponse.setPSPResponse(proPayInitiatePaymentResponse.getPsPInitiateResponse());
        }
        return initiatePaymentResponse;
    }
}
