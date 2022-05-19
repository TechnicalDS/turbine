package com.zee.turbine.facade.propay.dto;

import com.zee.turbine.constant.PaymentState;
import com.zee.turbine.constant.PaymentType;
import lombok.Data;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author Saba Imteyaz
 * @Date 16/03/22
 */
@Data
public class ProPayCallbackProcessResponse {

    private UUID orderId;

    private UUID providerId;

    private String providerName;

    private PaymentState paymentState;

    private PaymentType paymentType;

    private BigDecimal pgDiscount = new BigDecimal(0);

    private BigDecimal finalAmount = new BigDecimal(0);

    private String pspTxnId;

    private JSONObject callbackPSPResponse;

}
