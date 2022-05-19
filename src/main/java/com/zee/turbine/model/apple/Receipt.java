package com.zee.turbine.model.apple;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Saba Imteyaz
 * @Date 23/03/22
 */

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Receipt {

    @JsonProperty(value = "receipt_type")
    public String receiptType;

    @JsonProperty(value = "adam_id")
    public long adamId;

    @JsonProperty(value = "app_item_id")
    public String appItemId;

    @JsonProperty(value = "bundle_id")
    public String bundleId;

    @JsonProperty(value = "application_version")
    public String applicationVersion;

    @JsonProperty(value = "download_id")
    public String downloadId;

    @JsonProperty(value = "version_external_identifier")
    public String versionExternalIdentifier;

    @JsonProperty(value = "receipt_creation_date")
    public String receiptCreationDate;

    @JsonProperty(value = "receipt_creation_date_ms")
    public String receiptCreationDateMs;

    @JsonProperty(value = "receipt_creation_date_pst")
    public String receiptCreationDatePst;

    @JsonProperty(value = "request_date")
    public String requestDate;

    @JsonProperty(value = "request_date_ms")
    public String requestDateMs;

    @JsonProperty(value = "request_date_pst")
    private String requestDatePst;

    @JsonProperty(value = "original_purchase_date")
    private String originalPurchaseDate;

    @JsonProperty(value = "original_purchase_date_ms")
    private String originalPurchaseDateMs;

    @JsonProperty(value = "original_purchase_date_pst")
    private String originalPurchaseDatePst;

    @JsonProperty(value = "original_application_version")
    private String originalApplicationVersion;

    @JsonProperty(value = "in_app")
    private List<LatestReceiptInfo> inApp;

}
