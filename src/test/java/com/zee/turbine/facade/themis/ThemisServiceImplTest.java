package com.zee.turbine.facade.themis;

import com.zee.turbine.exception.TurbineException;
import com.zee.turbine.facade.themis.dto.ProviderRuleResponse;
import com.zee.turbine.feign.client.ThemisClient;
import com.zee.turbine.response.ZeePaymentAPIResponse;
import feign.FeignException;
import feign.Request;
import feign.RequestTemplate;
import feign.Response;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.webjars.NotFoundException;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;

import static com.zee.turbine.TestUtil.*;
import static com.zee.turbine.TestUtil.getProviderRuleRequest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Saba Imteyaz
 * @Date 19/04/2022
 */
@SpringBootTest
class ThemisServiceImplTest {

    @Mock
    ThemisClient themisClient;

    @InjectMocks
    ThemisServiceImpl themisService;

    @Test
    void getProviderDetail() {
        ResponseEntity<ZeePaymentAPIResponse<ProviderRuleResponse>> providerRuleResponseDetail = getProviderRuleResponseDetail();
        providerRuleResponseDetail.getBody().getData().setIsValidPayment(false);

        when(themisClient.validatePaymentProvider(any(), any())).thenReturn(providerRuleResponseDetail);
        assertThrows(TurbineException.class, () -> {
            themisService.getProviderDetail(getProviderRuleRequest());
        });

        when(themisClient.validatePaymentProvider(any(), any())).thenReturn(null);
        assertThrows(TurbineException.class, () -> {
            themisService.getProviderDetail(getProviderRuleRequest());
        });

        when(themisClient.validatePaymentProvider(any(), any())).thenReturn(getProviderRuleResponseDetail());
        ProviderRuleResponse result = themisService.getProviderDetail(getProviderRuleRequest());
        assertEquals(PROVIDER_ID, result.getProviderId());
        assertEquals(true, result.getIsValidPayment());

        when(themisClient.validatePaymentProvider(any(), any())).thenThrow(new NotFoundException(""));
        assertThrows(TurbineException.class, () -> {
            themisService.getProviderDetail(getProviderRuleRequest());
        });
    }
}
