package com.zee.turbine.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.json.JSONObject;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

/**
 * @author Saba Imteyaz
 * @Date 27/03/2022
 */

@Data
@Validated
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class S2sProcessRequest {

    private String providerName;

    @JsonIgnore
    private UUID providerId;

    private JSONObject s2sPSPInfo;

}
