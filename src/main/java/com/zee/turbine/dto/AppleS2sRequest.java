package com.zee.turbine.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.zee.turbine.model.apple.AppleReceiptResponse;
import com.zee.turbine.model.apple.AppleReceiptS2SResponse;
import com.zee.turbine.model.apple.LatestReceiptInfo;
import lombok.Data;

import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppleS2sRequest {

    private UUID paymentId;

    private String receiptToken;

    private String notificationType;

    private String transactionId;

    private String originalTransactionId;

    private String productId;

    private UUID orderId;

    public static AppleS2sRequest toDto(AppleReceiptS2SResponse appleReceiptS2SResponse, String receiptToken) {
        AppleS2sRequest appleS2sRequest = new AppleS2sRequest();
        appleS2sRequest.setNotificationType(appleReceiptS2SResponse.getNotificationType());
        appleS2sRequest.setReceiptToken(receiptToken);
        AppleReceiptResponse unifiedReceipt = appleReceiptS2SResponse.getUnifiedReceipt();
        if (unifiedReceipt != null &&
                unifiedReceipt.getLatestReceipt() != null &&
                unifiedReceipt.getLatestReceiptInfo().size()>0 ) {

            LatestReceiptInfo latestReceiptInfo = unifiedReceipt.getLatestReceiptInfo().get(0);
            appleS2sRequest.setTransactionId(latestReceiptInfo.getTransactionId());
            appleS2sRequest.setOriginalTransactionId(latestReceiptInfo.getOriginalTransactionId());
            appleS2sRequest.setProductId(latestReceiptInfo.getProductId());
        }
        return appleS2sRequest;
    }
}
