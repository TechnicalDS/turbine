package com.zee.turbine.constant;

/**
 * @author Saba Imteyaz
 * @Date 29/03/2022
 */

public enum PaymentType {
    CARD("card"),
    WALLET("wallet"),
    UPI("upi"),
    COUPON("coupon");
    private String value;

    PaymentType( String value) {
        this.value = value;
    }

    public static PaymentType fromValue(String text) {
        for (PaymentType b : PaymentType.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }
}
