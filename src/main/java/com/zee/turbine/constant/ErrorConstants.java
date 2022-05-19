package com.zee.turbine.constant;

/**
 * @author Saba Imteyaz
 * @Date 14/03/22
 */

public enum ErrorConstants {
    BAD_REQUEST_ERROR_CODE("TBN_400","bad Request, server can not process the request"),
    SERVER_INTERNAL_ERROR_CODE("TBN_500","Server Internal default error occurred"),
    UNAUTHORIZED_ERROR_CODE("TBN_401","UnAuthorized Request"),
    INVALID_S2S_ERROR_CODE("TBN_400","Invalid MerchantReference"),
    THEMIS_INTERNAL_SERVER_ERROR_CODE("TMS_500", "Themis service failed"),
    PROPAY_INTERNAL_SERVER_ERROR_CODE("PRO_500", "ProPay service failed"),
    PAYMENT_INTERNAL_SERVER_ERROR_CODE("PMT_500", "Payment service failed"),
    INVALID_RESPONSE_ERROR_CODE("TBN_500", "Invalid response in internal service call"),
    INVALID_RULE_ERROR_CODE("TBN_500", "Invalid provider rule");

    private String errorCode;
    private String errorMessage;

    ErrorConstants(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    @Override
    public String toString() {
        return String.valueOf(errorCode);
    }
    public static String getCode(ErrorConstants errorConstants) {
        for (ErrorConstants b : ErrorConstants.values()) {
            if (String.valueOf(b.errorCode).equals(errorConstants.errorCode)) {
                return b.errorCode;
            }
        }
        return null;
    }
    public static String getMessage(ErrorConstants errorConstants) {
        for (ErrorConstants b : ErrorConstants.values()) {
            if (String.valueOf(b.errorCode).equals(errorConstants.errorCode)) {
                return b.errorMessage;
            }
        }
        return null;
    }
}
