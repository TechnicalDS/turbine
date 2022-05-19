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
public class LatestReceiptInfo {

  @JsonProperty(value = "quantity")
  public int quantity;

  @JsonProperty(value = "product_id")
  public String productId;

  @JsonProperty(value = "transaction_id")
  public String transactionId;

  @JsonProperty(value = "original_transaction_id")
  public String originalTransactionId;

  @JsonProperty(value = "purchase_date")
  public String purchaseDate;

  @JsonProperty(value = "purchase_date_ms")
  public long purchaseDateMs;

  @JsonProperty(value = "purchase_date_pst")
  public String purchaseDatePst;

  @JsonProperty(value = "original_purchase_date")
  public String originalPurchaseDate;

  @JsonProperty(value = "original_purchase_date_ms")
  public String originalPurchaseDateMs;

  @JsonProperty(value = "original_purchase_date_pst")
  public String originalPurchaseDatePst;

  @JsonProperty(value = "is_trial_period")
  public boolean isTrialPeriod;

  @JsonProperty(value = "expires_date")
  public String expiresDate;

  @JsonProperty(value = "expires_date_ms" )
  public long expiresDateMs;

  @JsonProperty(value = "expires_date_pst")
  public String expiresDatePst;

  @JsonProperty(value = "web_order_line_item_id")
  public String webOrderLineItemId;

  @JsonProperty(value = "is_in_intro_offer_period")
  public String isInIntroOfferPeriod;
}
