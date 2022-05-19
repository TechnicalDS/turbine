package com.zee.turbine.constant;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Saba Imteyaz
 * @Date 18/04/2022
 */
public enum ProviderName {
    ADYEN_ZEE_5_APAC("AdyenZee5APAC", ApplicationConstants.ADYEN),
    ADYEN_ZEE_5_AFRICA("AdyenZee5Africa", ApplicationConstants.ADYEN),
    ADYEN_ZEE_5_AMERICAS("AdyenZee5Americas", ApplicationConstants.ADYEN),
    ADYEN_ZEE_5_EUROPE("AdyenZee5Europe", ApplicationConstants.ADYEN),
    ADYEN_ZEE_5_MIDDLE_EAST("AdyenZee5MiddleEast", ApplicationConstants.ADYEN),
    ADYEN("Adyen", ApplicationConstants.ADYEN),
    APPLE("Apple", ApplicationConstants.APPLE);

    private String value;
    private String providerName;

    private static final Map<String, String> ENUM_MAP;

    ProviderName(String value, String providerName) {
        this.value = value;
        this.providerName = providerName;
    }
    public String getValue() {
        return this.value;
    }
    public String getProviderName() {
        return this.providerName;
    }

    static {
        Map<String, String> map = new HashMap<String, String>();
        for (ProviderName instance : ProviderName.values()) {
            map.put(instance.getValue().toUpperCase(),instance.getProviderName());
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static String getProviderName(String value) {
        return ENUM_MAP.get(value.toUpperCase());
    }
}
