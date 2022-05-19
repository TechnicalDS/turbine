package com.zee.turbine.facade.olm.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.zee.turbine.facade.payment.dto.PaymentResponse;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Saba Imteyaz
 * @Date 05/04/2022
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class PaymentDetail {

    private String orderId;
    List<TransactionDetail> transactionDetails;

    public static PaymentDetail toDto(PaymentResponse paymentResponse) {
        PaymentDetail paymentDetail = new PaymentDetail();
        paymentDetail.setOrderId(paymentResponse.getOrderId().toString());

        List<TransactionDetail> transactionDetailList = new ArrayList<TransactionDetail>();
        transactionDetailList.add(TransactionDetail.toDto(paymentResponse));
        paymentDetail.setTransactionDetails(transactionDetailList);
        return paymentDetail;
    }

}
