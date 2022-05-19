package com.zee.turbine.facade.propay.dto;

import com.zee.turbine.dto.CallbackProcessRequest;
import lombok.Data;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author Saba Imteyaz
 * @Date 17/03/22
 */
@Data
public class ProPayCallbackProcessRequest {

    private UUID orderId;

    private UUID providerId ;

    private String providerName;

    private BigDecimal pgDiscount = new BigDecimal(0.0);

    private BigDecimal finalAmount = new BigDecimal(0.0);

    private JSONObject callbackPSPProcessRequest;

    public static ProPayCallbackProcessRequest toDto(CallbackProcessRequest callbackProcessRequest) {
        ProPayCallbackProcessRequest proPayCallbackProcessRequest = new ProPayCallbackProcessRequest();
        proPayCallbackProcessRequest.setOrderId(callbackProcessRequest.getOrderId());
        proPayCallbackProcessRequest.setProviderId(callbackProcessRequest.getProviderId());
        proPayCallbackProcessRequest.setProviderName(callbackProcessRequest.getProviderName());
        proPayCallbackProcessRequest.setCallbackPSPProcessRequest(callbackProcessRequest.getPSPInfo());
        return proPayCallbackProcessRequest;
    }
}
