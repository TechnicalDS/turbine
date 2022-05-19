package com.zee.turbine.model.apple;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Saba Imteyaz
 * @Date 26/03/22
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppleReceiptResponse {

    @JsonProperty(value = "status")
    private long status;
    @JsonProperty(value = "environment")
    private String environment;
    @JsonProperty(value = "receipt")
    private Receipt receipt;
    @JsonProperty(value = "latest_receipt_info")
    private List<LatestReceiptInfo> latestReceiptInfo;
    @JsonProperty(value = "latest_receipt")
    private String latestReceipt;
    @JsonProperty(value = "pending_renewal_info")
    private List<PendingRenewalInfo> pendingRenewalInfo;

}
