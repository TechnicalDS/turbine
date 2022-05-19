package com.zee.turbine.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.zee.turbine.constant.PaymentState;
import com.zee.turbine.facade.payment.dto.PaymentResponse;
import com.zee.turbine.facade.propay.dto.ProPayCallbackProcessResponse;
import lombok.Data;
import org.json.JSONObject;

import java.util.UUID;

/**
 * @author Saba Imteyaz
 * @Date 14/03/22
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CallbackProcessResponse {
    private UUID orderId;
    private UUID paymentId;
    private PaymentState paymentState;
    private JSONObject responseMessage;

    /**
     *
     * @param paymentResponse
     * @param proPayCallbackProcessResponse
     * @return CallbackProcessResponse
     * This method converts to CallbackProcessResponse Dto
     */
    public static CallbackProcessResponse toDto(PaymentResponse paymentResponse, ProPayCallbackProcessResponse proPayCallbackProcessResponse) {
        CallbackProcessResponse callbackProcessResponse = new CallbackProcessResponse();
        callbackProcessResponse.setOrderId(proPayCallbackProcessResponse.getOrderId());
        callbackProcessResponse.setPaymentId(paymentResponse.getPaymentId());
        callbackProcessResponse.setPaymentState(paymentResponse.getPaymentState());
        callbackProcessResponse.setResponseMessage(proPayCallbackProcessResponse.getCallbackPSPResponse());
        return callbackProcessResponse;
    }

}
