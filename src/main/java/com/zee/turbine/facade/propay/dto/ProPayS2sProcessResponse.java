package com.zee.turbine.facade.propay.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.json.JSONObject;

import java.util.UUID;

/**
 * @author Saba Imteyaz
 * @Date 30/03/2022
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude
public class ProPayS2sProcessResponse {

    private UUID providerId;

    private String providerName;

    private JSONObject s2sPSPResponse;
}
