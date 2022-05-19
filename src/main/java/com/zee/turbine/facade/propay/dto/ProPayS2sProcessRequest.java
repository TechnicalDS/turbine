package com.zee.turbine.facade.propay.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.zee.turbine.dto.S2sProcessRequest;
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
public class ProPayS2sProcessRequest {

    private UUID providerId;

    private String providerName;

    private JSONObject s2sPSPProcessRequest;

    public static ProPayS2sProcessRequest toDto(S2sProcessRequest s2sProcessRequest) {
        ProPayS2sProcessRequest proPayS2sProcessRequest = new ProPayS2sProcessRequest();
        proPayS2sProcessRequest.setS2sPSPProcessRequest(s2sProcessRequest.getS2sPSPInfo());
        proPayS2sProcessRequest.setProviderName(s2sProcessRequest.getProviderName());
        proPayS2sProcessRequest.setProviderId(s2sProcessRequest.getProviderId());
        return proPayS2sProcessRequest;
    }
}
