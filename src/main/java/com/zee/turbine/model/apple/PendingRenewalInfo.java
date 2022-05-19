package com.zee.turbine.model.apple;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author Saba Imteyaz
 * @Date 23/03/22
 */

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PendingRenewalInfo {

    @JsonProperty(value = "expiration_intent")
    public String expirationIntent;

    @JsonProperty(value = "auto_renew_product_id")
    public String autoRenewProductId;

    @JsonProperty(value = "original_transaction_id")
    public String originalTransactionId;

    @JsonProperty(value = "is_in_billing_retry_period")
    public String isInBillingRetryPeriod;

    @JsonProperty(value = "product_id")
    public String productId;

    @JsonProperty(value = "auto_renew_status")
    public int autoRenewStatus;

    /**
     * The time at which the grace period for subscription renewals expires,
     * in a date-time format similar to the ISO 8601
     */
    @JsonProperty(value = "grace_period_expires_date")
    public String gracePeriodExpiresDate;

    /**
     * The time at which the grace period for subscription renewals expires, in UNIX epoch time format, in milliseconds.
     * This key is only present for apps that have Billing Grace Period enabled
     * and when the user experiences a billing error at the time of renewal.
     * Use this time format for processing dates.
     */

    @JsonProperty(value = "grace_period_expires_date_ms")
    public String gracePeriodExpiresDateMs;

    /**
     * The time at which the grace period for subscription renewals expires, in the Pacific Time zone.
     */
    @JsonProperty(value = "grace_period_expires_date_pst")
    public String gracePeriodExpiresDatePst;

    /**
     * The reference name of a subscription offer that you configured in App Store Connect.
     * This field is present when a customer redeemed a subscription offer code.
     */
    @JsonProperty(value = "offer_code_ref_name")
    public String offerCodeRefName;

    /**
     * The price consent status for a subscription price increase.
     * This field is only present if the customer was notified of the price increase.
     * The default value is "0" and changes to "1" if the customer consents.
     */
    @JsonProperty(value = "price_consent_status")
    public String priceConsentStatus;

    /**
     * The identifier of the promotional offer for an auto-renewable subscription that the user redeemed.
     * You provide this value in the Promotional Offer Identifier field
     * when you create the promotional offer in App Store Connect.
     */
    @JsonProperty(value = "promotional_offer_id")
    public String promotionalOfferId;

}
