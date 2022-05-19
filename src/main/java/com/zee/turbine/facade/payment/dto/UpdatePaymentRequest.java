package com.zee.turbine.facade.payment.dto;

import com.zee.turbine.constant.PaymentState;
import com.zee.turbine.dto.CallbackProcessRequest;
import com.zee.turbine.facade.propay.dto.ProPayCallbackProcessResponse;
import lombok.Data;
import org.json.JSONObject;

import java.util.Date;
import java.util.UUID;

/**
 * @author Saba Imteyaz
 * @Date 17/03/22
 */
@Data
public class UpdatePaymentRequest {

    private UUID paymentId;

    private UUID orderId;

    private UUID providerId;

    private String providerName;

    private PaymentState paymentState;

    private String pspTxnId;

    private Date transactionDate;

    private String paymentDescription;

    private Boolean s2s;

    private JSONObject transactionDetails;

    public static UpdatePaymentRequest toDto(ProPayCallbackProcessResponse proPayCallbackProcessResponse, String description) {
        UpdatePaymentRequest updatePaymentRequest = new UpdatePaymentRequest();
        updatePaymentRequest.setOrderId(proPayCallbackProcessResponse.getOrderId());
        updatePaymentRequest.setProviderId(proPayCallbackProcessResponse.getProviderId());
        updatePaymentRequest.setProviderName(proPayCallbackProcessResponse.getProviderName());
        updatePaymentRequest.setPaymentState(proPayCallbackProcessResponse.getPaymentState());
        updatePaymentRequest.setPspTxnId(proPayCallbackProcessResponse.getPspTxnId());
        updatePaymentRequest.setS2s(false);
        updatePaymentRequest.setTransactionDate(new Date());
        updatePaymentRequest.setTransactionDetails(proPayCallbackProcessResponse.getCallbackPSPResponse());
        updatePaymentRequest.setPaymentDescription(description);
        return updatePaymentRequest;
    }

    public static UpdatePaymentRequest toDto(CallbackProcessRequest callbackProcessRequest, String errorMessage) {
        UpdatePaymentRequest updatePaymentRequest = new UpdatePaymentRequest();
        updatePaymentRequest.setProviderName(callbackProcessRequest.getProviderName());
        updatePaymentRequest.setOrderId(callbackProcessRequest.getOrderId());
        updatePaymentRequest.setS2s(false);
        updatePaymentRequest.setPaymentState(PaymentState.FAILURE);
        updatePaymentRequest.setPaymentDescription("Apple In-App purchase failed: " + errorMessage);
        return updatePaymentRequest;
    }

    public static UpdatePaymentRequest toDto(JSONObject s2sTransactionDetails, String pspTxnId) {
        UpdatePaymentRequest updatePaymentRequest = new UpdatePaymentRequest();
        updatePaymentRequest.setPspTxnId(pspTxnId);
        updatePaymentRequest.setS2s(true);
        updatePaymentRequest.setTransactionDetails(s2sTransactionDetails);
        return updatePaymentRequest;
    }
}
