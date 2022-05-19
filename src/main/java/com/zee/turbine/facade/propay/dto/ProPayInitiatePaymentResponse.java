package com.zee.turbine.facade.propay.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import org.json.JSONObject;

import java.util.UUID;

/**
 * @author Saba Imteyaz
 * @Date 14/03/22
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ProPayInitiatePaymentResponse {
    private UUID orderId;
    private JsonNode psPInitiateResponse;
}
