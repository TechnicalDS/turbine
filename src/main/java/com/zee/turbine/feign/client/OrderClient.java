package com.zee.turbine.feign.client;

import com.zee.turbine.facade.olm.dto.PaymentDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.zee.turbine.constant.ApplicationConstants.ORDER_ISC_KEY_NAME;

/**
 * @author Saba Imteyaz
 * @Date 14/03/22
 */

/**
 * Feign client to call order service apis
 */
@FeignClient(value="order-client", url="${order.order-url}")
public interface OrderClient {

    @RequestMapping(method=RequestMethod.POST, value="${order.update-order}")
    ResponseEntity<String> updateOrder(@RequestHeader(ORDER_ISC_KEY_NAME) String orderKey,
                                              @RequestBody PaymentDetail paymentDetail);
}
