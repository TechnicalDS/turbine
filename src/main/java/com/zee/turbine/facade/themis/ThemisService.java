package com.zee.turbine.facade.themis;

import com.zee.turbine.facade.themis.dto.ProviderRuleRequest;
import com.zee.turbine.facade.themis.dto.ProviderRuleResponse;
import org.springframework.stereotype.Component;

/**
 * @author Saba Imteyaz
 * @Date 30/03/2022
 */

@Component
public interface ThemisService {
    ProviderRuleResponse getProviderDetail(ProviderRuleRequest providerRuleRequest);
}
