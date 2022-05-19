package com.zee.turbine.model.adyen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Saba Imteyaz
 * @Date 27/03/2022
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationRequestItem {

    @JsonProperty("merchantReference")
    private String merchantReference;

    @JsonProperty("success")
    private Boolean success;

    @JsonProperty("pspReference")
    private String pspReference;

    @JsonProperty("paymentMethod")
    private String paymentMethod;

    @JsonProperty("merchantAccountCode")
    private String merchantAccountCode;

    @JsonProperty("originalReference")
    private String originalReference;

    @JsonProperty("eventDate")
    private String eventDate;

    @JsonProperty("eventCode")
    private String eventCode;

    @JsonProperty("reason")
    private String reason;

    @JsonProperty("amount")
    private Amount amount;

    @JsonProperty("additionalData")
    private AdditionalData additionalData;
}
