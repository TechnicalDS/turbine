package com.zee.turbine.facade.olm;

import com.zee.turbine.facade.olm.dto.PaymentDetail;

/**
 * @author Saba Imteyaz
 * @Date 06/04/2022
 */
public interface OrderService {

    String updateOrder(PaymentDetail paymentDetail);
}
