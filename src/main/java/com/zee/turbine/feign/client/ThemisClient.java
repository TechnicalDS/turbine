package com.zee.turbine.feign.client;

import com.zee.turbine.response.ZeePaymentAPIResponse;
import com.zee.turbine.facade.themis.dto.ProviderRuleRequest;
import com.zee.turbine.facade.themis.dto.ProviderRuleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.zee.turbine.constant.ApplicationConstants.THEMIS_ISC_KEY_NAME;


@FeignClient(value="themis-client", url="${themis.themis-url}")
public interface ThemisClient {

    @RequestMapping(method = RequestMethod.GET, value = "${themis.provider-path}")
    ResponseEntity<ZeePaymentAPIResponse<ProviderRuleResponse>> validatePaymentProvider(@RequestHeader(THEMIS_ISC_KEY_NAME) String themisKey,
                                                                                        @RequestBody ProviderRuleRequest providerRuleRequest);

}