package com.zee.turbine.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.zee.turbine.facade.propay.dto.ProPayS2sProcessResponse;
import lombok.Data;
import org.json.JSONObject;

/**
 * @author Saba Imteyaz
 * @Date 27/03/2022
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class S2sProcessResponse {

    private String providerName;

    private JSONObject responseMessage;

    public static S2sProcessResponse toS2sProcessResponse(ProPayS2sProcessResponse proPayS2sProcessResponse) {
        S2sProcessResponse s2sProcessResponse = new S2sProcessResponse();
        s2sProcessResponse.setProviderName(proPayS2sProcessResponse.getProviderName());
        s2sProcessResponse.setResponseMessage(proPayS2sProcessResponse.getS2sPSPResponse());
        return s2sProcessResponse;
    }

    public static S2sProcessResponse toDto(S2sProcessRequest s2sProcessRequest, String responseMessage) {
        S2sProcessResponse s2sProcessResponse = new S2sProcessResponse();
        s2sProcessResponse.setProviderName(s2sProcessRequest.getProviderName());
        s2sProcessResponse.setResponseMessage(new JSONObject().put("message", responseMessage));
        return s2sProcessResponse;
    }

}
