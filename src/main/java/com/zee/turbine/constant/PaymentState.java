package com.zee.turbine.constant;

/**
 * @author Saba Imteyaz
 * @Date 15/03/22
 */

public enum PaymentState {
    INITIATED("INITIATED"),

    SUCCESS("SUCCESS"),

    FAILURE("FAILURE"),

    PENDING("PENDING");

    private String value;

    PaymentState(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static PaymentState fromValue(String text) {
        for (PaymentState b : PaymentState.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }
}
