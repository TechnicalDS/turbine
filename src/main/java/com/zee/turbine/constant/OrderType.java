package com.zee.turbine.constant;

/**
 * @author Saba Imteyaz
 * @Date 11/03/22
 */

public enum OrderType {
    PURCHASE("PURCHASE"),
    SUBSCRIPTION("SUBSCRIPTION"),
    DONATION("DONATION");
    private String value;

    OrderType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static OrderType fromValue(String text) {
        for (OrderType b : OrderType.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }
}
