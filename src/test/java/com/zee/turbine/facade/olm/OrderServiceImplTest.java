package com.zee.turbine.facade.olm;

import com.zee.turbine.exception.TurbineException;
import com.zee.turbine.feign.client.OrderClient;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.webjars.NotFoundException;

import static com.zee.turbine.TestUtil.getOrderResponseDetail;
import static com.zee.turbine.TestUtil.getPaymentDetail;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Saba Imteyaz
 * @Date 22/04/2022
 */
@SpringBootTest
class OrderServiceImplTest {

    @Mock
    OrderClient orderClient;

    @InjectMocks
    OrderServiceImpl orderService;

    @Test
    void updateOrder() {
        when(orderClient.updateOrder(any(), any())).thenReturn(null);
        assertThrows(TurbineException.class, () -> {
            orderService.updateOrder(getPaymentDetail());
        });


        when(orderClient.updateOrder(any(), any())).thenReturn(getOrderResponseDetail());
        String result = orderService.updateOrder(getPaymentDetail());
        assertEquals("Success", result);

        when(orderClient.updateOrder(any(), any())).thenThrow(new NotFoundException("Exception occurred"));
        assertThrows(Exception.class, () -> {
            orderService.updateOrder(getPaymentDetail());
        });
    }
}