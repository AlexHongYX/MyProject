package com.fehead.service;

import com.fehead.error.BussinessException;
import com.fehead.service.model.OrderModel;

/**
 * Created by xiaoaxiao on 2019/5/11
 * Description:
 */
public interface OrderService {

    /**
     * 根据userId，itemId，amount创建订单信息
     * @param userId
     * @param itemId
     * @param amount
     * @return
     */
    OrderModel createOrder(Integer userId,Integer itemId,Integer amount) throws BussinessException;
}
