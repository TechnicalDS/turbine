package com.zee.turbine.facade.olm.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.zee.turbine.constant.PaymentState;
import com.zee.turbine.constant.PaymentType;
import com.zee.turbine.model.apple.AppleReceiptResponse;
import com.zee.turbine.facade.payment.dto.PaymentResponse;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author Saba Imteyaz
 * @Date 05/04/2022
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDetail {

    private UUID paymentId;
    private BigDecimal transactionAmount = new BigDecimal(0.0);
    private BigDecimal pgDiscountPercentage = new BigDecimal(0.0);
    private BigDecimal pgDiscountAmt = new BigDecimal(0.0);
    private BigDecimal transactionFinalAmount = new BigDecimal(0.0);
    private PaymentState paymentState;
    private PaymentType paymentType;
    private UUID recurringMandateId;
    private String SubStartDate;
    private String SubEndDate;
    private String paymentRemark;


    public static TransactionDetail toDto(PaymentResponse paymentResponse) {
        TransactionDetail transactionDetail = new TransactionDetail();
        transactionDetail.setPaymentId(paymentResponse.getPaymentId());
        transactionDetail.setTransactionAmount(paymentResponse.getOrderAmount());
        transactionDetail.setPgDiscountAmt(paymentResponse.getDiscount());
        transactionDetail.setTransactionFinalAmount(paymentResponse.getOrderAmount().subtract(paymentResponse.getDiscount()));
        transactionDetail.setPaymentState(paymentResponse.getPaymentState());
        transactionDetail.setPaymentType(paymentResponse.getPaymentType());
        transactionDetail.setRecurringMandateId(paymentResponse.getRecurringMandateId());
        transactionDetail.setPaymentRemark(paymentResponse.getPaymentDescription());
        return transactionDetail;
    }

    public static TransactionDetail toDto(PaymentResponse paymentResponse, AppleReceiptResponse appleReceiptResponse) {
        TransactionDetail transactionDetail = toDto(paymentResponse);

        return transactionDetail;
    }
}