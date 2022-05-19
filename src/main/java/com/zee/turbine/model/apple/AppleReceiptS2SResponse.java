package com.zee.turbine.model.apple;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author Saba Imteyaz
 * @Date 26/03/22
 */

/**
 *  Apple S2S response dto
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppleReceiptS2SResponse {

    @JsonProperty(value = "unified_receipt")
    private AppleReceiptResponse unifiedReceipt;

    @JsonProperty(value = "environment")
    private String environment;

    @JsonProperty(value = "auto_renew_product_id")
    private String autoRenewProductId;

    @JsonProperty(value = "auto_renew_status")
    private Boolean autoRenewStatus;

    @JsonProperty(value = "bid")
    private String bId;

    @JsonProperty(value = "bvrs")
    private String bvrs;

    @JsonProperty(value = "notification_type")
    private String notificationType;

    @JsonProperty(value = "password")
    private String password;


}
