package com.zee.turbine.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NonNull;
import org.json.JSONObject;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

/**
 * @author Saba Imteyaz
 * @Date 14/03/22
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Validated
@Data
public class CallbackProcessRequest {

    private String providerName;

    private UUID orderId;

    @JsonIgnore
    private UUID providerId;

    private JSONObject PSPInfo;

}
