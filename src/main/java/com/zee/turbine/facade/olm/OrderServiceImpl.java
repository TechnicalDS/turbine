package com.zee.turbine.facade.olm;

import com.zee.turbine.constant.ErrorConstants;
import com.zee.turbine.exception.TurbineException;
import com.zee.turbine.feign.client.OrderClient;
import com.zee.turbine.facade.olm.dto.PaymentDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static com.zee.turbine.constant.ErrorConstants.INVALID_RESPONSE_ERROR_CODE;

/**
 * @author Saba Imteyaz
 * @Date 06/04/2022
 */

@Component
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderClient orderClient;

    @Value("${order.order-isc-key}")
    private String orderIscKey;

    @Override
    public String updateOrder(PaymentDetail paymentDetail) {
        log.debug("start calling order service process updateOrder API");
         ResponseEntity<String> orderResponseDetail = null;
         try{
            orderResponseDetail = orderClient.updateOrder(orderIscKey, paymentDetail);
        } catch (Exception e) {
            log.error("Exception while calling Order service for orderId {} => {}", paymentDetail.getOrderId(), e.getMessage());
            throw e;
        }

        if (orderResponseDetail == null
                || orderResponseDetail.getBody() == null) {
            log.error("Update order response is not valid: {}", orderResponseDetail);
            throw new TurbineException(ErrorConstants.getCode(INVALID_RESPONSE_ERROR_CODE),
                    ErrorConstants.getMessage(INVALID_RESPONSE_ERROR_CODE));
        }
        String orderResponse = orderResponseDetail.getBody();
        log.debug("end order service process updateOrder API olmResponse: {}", orderResponse);
        return orderResponse;
    }
}
