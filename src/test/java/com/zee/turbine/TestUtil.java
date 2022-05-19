package com.zee.turbine;

import com.zee.turbine.constant.PaymentState;
import com.zee.turbine.dto.*;
import com.zee.turbine.facade.olm.dto.PaymentDetail;
import com.zee.turbine.facade.olm.dto.TransactionDetail;
import com.zee.turbine.facade.payment.dto.CreatePaymentRequest;
import com.zee.turbine.facade.payment.dto.PaymentResponse;
import com.zee.turbine.facade.payment.dto.UpdatePaymentRequest;
import com.zee.turbine.facade.propay.dto.ProPayCallbackProcessResponse;
import com.zee.turbine.facade.propay.dto.ProPayInitiatePaymentResponse;
import com.zee.turbine.facade.propay.dto.ProPayS2sProcessResponse;
import com.zee.turbine.facade.themis.dto.ProviderRuleRequest;
import com.zee.turbine.facade.themis.dto.ProviderRuleResponse;
import com.zee.turbine.model.apple.ApplePSPInfo;
import com.zee.turbine.model.apple.AppleReceiptS2SResponse;
import com.zee.turbine.response.ZeePaymentAPIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Saba Imteyaz
 * @Date 18/04/2022
 */
public interface TestUtil {

    UUID ORDER_ID = UUID.fromString("5cf9ab8f-fd7e-4da7-9f4a-ffac51b672ca");
    UUID PAYMENT_ID = UUID.fromString("2ac9b6ba-c9bc-436c-9cab-cb667a2e8d57");
    UUID PROVIDER_ID = UUID.fromString("5cf9ab8f-fd7e-4da7-9f4a-ffac51b672ca");
    String PROVIDER_NAME = "APPLE";
    PaymentState PAYMENT_SUCCESS = PaymentState.SUCCESS;

    static InitiatePaymentRequest getInitiatePaymentRequest() {
        InitiatePaymentRequest initiatePaymentRequest = new InitiatePaymentRequest();
        initiatePaymentRequest.setProviderName(PROVIDER_NAME);
        initiatePaymentRequest.setProviderId(PROVIDER_ID);
        initiatePaymentRequest.setOrderId(ORDER_ID);
        return initiatePaymentRequest;
    }

    static ProviderRuleResponse getProviderRuleResponse() {
        ProviderRuleResponse providerRuleResponse = new ProviderRuleResponse();
        providerRuleResponse.setProviderId(PROVIDER_ID);
        providerRuleResponse.setIsValidPayment(true);
        return providerRuleResponse;
    }

    static ZeePaymentAPIResponse<InitiatePaymentResponse> getInitiatePaymentResponse() {
        InitiatePaymentResponse initiatePaymentResponse = new InitiatePaymentResponse();
        initiatePaymentResponse.setOrderId(ORDER_ID);
        initiatePaymentResponse.setPaymentId(PAYMENT_ID);
        ZeePaymentAPIResponse<InitiatePaymentResponse> response = new ZeePaymentAPIResponse<InitiatePaymentResponse>();
        return response.buildSuccessResponse(0,"", initiatePaymentResponse);
    }

    static CallbackProcessRequest getCallbackProcessRequest() {
        CallbackProcessRequest callbackProcessRequest = new CallbackProcessRequest();
        callbackProcessRequest.setProviderId(PROVIDER_ID);
        callbackProcessRequest.setProviderName(PROVIDER_NAME);
        callbackProcessRequest.setOrderId(ORDER_ID);
        return callbackProcessRequest;
    }

    static ZeePaymentAPIResponse<CallbackProcessResponse> getCallbackProcessResponse() {
        CallbackProcessResponse callbackProcessResponse = new CallbackProcessResponse();
        callbackProcessResponse.setPaymentId(PAYMENT_ID);
        callbackProcessResponse.setOrderId(ORDER_ID);
        callbackProcessResponse.setPaymentState(PAYMENT_SUCCESS);

        ZeePaymentAPIResponse<CallbackProcessResponse> response = new ZeePaymentAPIResponse<CallbackProcessResponse>();
        return response.buildSuccessResponse(0,"", callbackProcessResponse);
    }

    static S2sProcessRequest getS2sProcessRequest() {
        S2sProcessRequest s2sProcessRequest = new S2sProcessRequest();
        s2sProcessRequest.setProviderName(PROVIDER_NAME);
        s2sProcessRequest.setProviderId(PROVIDER_ID);
        return s2sProcessRequest;
    }

    static ZeePaymentAPIResponse<S2sProcessResponse> getS2sProcessResponse() {
        S2sProcessResponse s2sProcessResponse = new S2sProcessResponse();
        s2sProcessResponse.setProviderName(PROVIDER_NAME);
        ZeePaymentAPIResponse<S2sProcessResponse> response = new ZeePaymentAPIResponse<S2sProcessResponse>();
        return response.buildSuccessResponse(0,"", s2sProcessResponse);
    }

    static ProPayInitiatePaymentResponse getProPayInitiatePaymentResponse() {
        ProPayInitiatePaymentResponse proPayInitiatePaymentResponse = new ProPayInitiatePaymentResponse();
        proPayInitiatePaymentResponse.setOrderId(ORDER_ID);
        return proPayInitiatePaymentResponse;
    }

    static PaymentResponse getPaymentResponse() {
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setPaymentId(PAYMENT_ID);
        paymentResponse.setOrderId(ORDER_ID);
        paymentResponse.setPaymentState(PAYMENT_SUCCESS);
        return  paymentResponse;
    }

    static ProPayCallbackProcessResponse getProPayCallbackProcessResponse() {
        ProPayCallbackProcessResponse proPayCallbackProcessResponse = new ProPayCallbackProcessResponse();
        proPayCallbackProcessResponse.setPaymentState(PAYMENT_SUCCESS);
        proPayCallbackProcessResponse.setOrderId(ORDER_ID);
        return proPayCallbackProcessResponse;
    }

    static ProPayS2sProcessResponse getProPayS2sProcessResponse() {
        ProPayS2sProcessResponse proPayS2sProcessResponse = new ProPayS2sProcessResponse();
        proPayS2sProcessResponse.setProviderId(PROVIDER_ID);
        proPayS2sProcessResponse.setProviderName(PROVIDER_NAME);
        return proPayS2sProcessResponse;
    }

    static ApplePSPInfo getApplePSPInfo() {
        ApplePSPInfo applePSPInfo = new ApplePSPInfo();
        applePSPInfo.setToken(null);
        applePSPInfo.setMessage("Exception occurred");
        return applePSPInfo;
    }

    static AppleReceiptS2SResponse getAppleReceiptS2SResponse() {
        AppleReceiptS2SResponse appleReceiptS2SResponse = new AppleReceiptS2SResponse();
        appleReceiptS2SResponse.setNotificationType("renewal");
        return appleReceiptS2SResponse;
    }

    static ProviderRuleRequest getProviderRuleRequest() {
        ProviderRuleRequest providerRuleRequest = new ProviderRuleRequest();
        providerRuleRequest.setProviderName(PROVIDER_NAME);
        return providerRuleRequest;
    }

    static ResponseEntity<ZeePaymentAPIResponse<ProviderRuleResponse>> getProviderRuleResponseDetail() {
        ZeePaymentAPIResponse<ProviderRuleResponse> providerRuleResponseZeePaymentAPIResponse = new ZeePaymentAPIResponse<ProviderRuleResponse>();
        providerRuleResponseZeePaymentAPIResponse.buildSuccessResponse(0, "", getProviderRuleResponse());
        return new ResponseEntity<ZeePaymentAPIResponse<ProviderRuleResponse>>(providerRuleResponseZeePaymentAPIResponse, HttpStatus.OK);
    }

    static ResponseEntity<ZeePaymentAPIResponse<ProPayInitiatePaymentResponse>> getProPayInitiatePaymentResponseDetail() {
        ZeePaymentAPIResponse<ProPayInitiatePaymentResponse> prpPayInitiatePaymentResponseZeePaymentAPIResponse = new ZeePaymentAPIResponse<ProPayInitiatePaymentResponse>();
        prpPayInitiatePaymentResponseZeePaymentAPIResponse.buildSuccessResponse(0, "", getProPayInitiatePaymentResponse());
        return new ResponseEntity<ZeePaymentAPIResponse<ProPayInitiatePaymentResponse>>(prpPayInitiatePaymentResponseZeePaymentAPIResponse, HttpStatus.OK);
    }

    static ResponseEntity<ZeePaymentAPIResponse<ProPayCallbackProcessResponse>> getProPayCallbackProcessResponseDetail() {
        ZeePaymentAPIResponse<ProPayCallbackProcessResponse> proPayCallbackProcessResponseZeePaymentAPIResponse = new ZeePaymentAPIResponse<ProPayCallbackProcessResponse>();
        proPayCallbackProcessResponseZeePaymentAPIResponse.buildSuccessResponse(0, "", getProPayCallbackProcessResponse());
        return new ResponseEntity<ZeePaymentAPIResponse<ProPayCallbackProcessResponse>>(proPayCallbackProcessResponseZeePaymentAPIResponse, HttpStatus.OK);
    }

    static ResponseEntity<ZeePaymentAPIResponse<ProPayS2sProcessResponse>> getProPayS2sProcessResponseDetail() {
        ZeePaymentAPIResponse<ProPayS2sProcessResponse> proPayS2sProcessResponseZeePaymentAPIResponse = new ZeePaymentAPIResponse<ProPayS2sProcessResponse>();
        proPayS2sProcessResponseZeePaymentAPIResponse.buildSuccessResponse(0, "", getProPayS2sProcessResponse());
        return new ResponseEntity<ZeePaymentAPIResponse<ProPayS2sProcessResponse>>(proPayS2sProcessResponseZeePaymentAPIResponse, HttpStatus.OK);
    }

    static ResponseEntity<ZeePaymentAPIResponse<PaymentResponse>> getPaymentResponseDetail() {
        ZeePaymentAPIResponse<PaymentResponse> paymentResponseZeePaymentAPIResponse = new ZeePaymentAPIResponse<PaymentResponse>();
        paymentResponseZeePaymentAPIResponse.buildSuccessResponse(0, "", getPaymentResponse());
        return new ResponseEntity<ZeePaymentAPIResponse<PaymentResponse>>(paymentResponseZeePaymentAPIResponse, HttpStatus.OK);
    }

    static CreatePaymentRequest getCreatePaymentRequest() {
        CreatePaymentRequest createPaymentRequest = new CreatePaymentRequest();
        createPaymentRequest.setOrderId(ORDER_ID);
        createPaymentRequest.setProviderId(PROVIDER_ID);
        createPaymentRequest.setProviderName(PROVIDER_NAME);
        return createPaymentRequest;
    }

    static UpdatePaymentRequest getUpdatePaymentRequest() {
        UpdatePaymentRequest updatePaymentRequest = new UpdatePaymentRequest();
        updatePaymentRequest.setOrderId(ORDER_ID);
        updatePaymentRequest.setProviderId(PROVIDER_ID);
        updatePaymentRequest.setProviderName(PROVIDER_NAME);
        updatePaymentRequest.setPaymentState(PAYMENT_SUCCESS);
        return updatePaymentRequest;
    }

    static ResponseEntity<String> getOrderResponseDetail() {
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    static PaymentDetail getPaymentDetail() {
        TransactionDetail transactionDetail = new TransactionDetail();
        transactionDetail.setPaymentId(PAYMENT_ID);
        transactionDetail.setPaymentState(PAYMENT_SUCCESS);
        List<TransactionDetail> transactionDetailList = new ArrayList<TransactionDetail>();
        transactionDetailList.add(transactionDetail);

        PaymentDetail paymentDetail = new PaymentDetail();
        paymentDetail.setOrderId(ORDER_ID.toString());
        paymentDetail.setTransactionDetails(transactionDetailList);
        return paymentDetail;
    }
}
