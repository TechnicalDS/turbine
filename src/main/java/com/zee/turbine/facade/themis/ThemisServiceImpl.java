package com.zee.turbine.facade.themis;

import com.zee.turbine.constant.ErrorConstants;
import com.zee.turbine.exception.TurbineException;
import com.zee.turbine.feign.client.ThemisClient;
import com.zee.turbine.response.ZeePaymentAPIResponse;
import com.zee.turbine.facade.themis.dto.ProviderRuleRequest;
import com.zee.turbine.facade.themis.dto.ProviderRuleResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static com.zee.turbine.constant.ErrorConstants.*;

/**
 * @author Saba Imteyaz
 * @Date 30/03/2022
 */

@Component
@Slf4j
public class ThemisServiceImpl implements ThemisService {

    @Autowired
    private ThemisClient themisClient;

    @Value("${themis.themis-isc-key}")
    private String themisIscKey;

    @Override
    public ProviderRuleResponse getProviderDetail(ProviderRuleRequest providerRuleRequest) {
        log.debug("start calling Themis Service validatePaymentProvider API ProviderRuleRequest: {}", providerRuleRequest);
        ResponseEntity<ZeePaymentAPIResponse<ProviderRuleResponse>> providerResponseDetail = null;
        try{
            providerResponseDetail = themisClient.validatePaymentProvider(themisIscKey,
                    providerRuleRequest);
        } catch (Exception e) {
            log.error("Exception while calling themis service: {}", e.getMessage());
            throw new TurbineException(ErrorConstants.getCode(THEMIS_INTERNAL_SERVER_ERROR_CODE),
                    ErrorConstants.getMessage(THEMIS_INTERNAL_SERVER_ERROR_CODE));
        }

        if (providerResponseDetail == null
                || providerResponseDetail.getBody() == null
                || !providerResponseDetail.getBody().getSuccess()
                || providerResponseDetail.getBody().getData() == null) {
            log.error("Provider rule response is not valid: {}", providerResponseDetail);
            throw new TurbineException(ErrorConstants.getCode(INVALID_RESPONSE_ERROR_CODE),
                    ErrorConstants.getMessage(INVALID_RESPONSE_ERROR_CODE));
        }
        ProviderRuleResponse providerRuleResponse = providerResponseDetail.getBody().getData();

        if (!providerRuleResponse.getIsValidPayment()) {
            log.error("Provider rule validation failed: {}", providerRuleResponse);
            throw new TurbineException(ErrorConstants.getCode(INVALID_RULE_ERROR_CODE),
                    ErrorConstants.getMessage(INVALID_RULE_ERROR_CODE));
        }
        log.debug("end calling Themis Service validate payment provider API providerRuleResponse: {}", providerRuleResponse);
        return providerRuleResponse;
    }
}
