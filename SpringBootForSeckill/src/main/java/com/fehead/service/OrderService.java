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
    //通过前端url上传过来秒杀活动id，然后下单接口内校验对应id是否属于对应商品且活动已经开始
    OrderModel createOrder(Integer userId,Integer itemId,Integer promoId,Integer amount) throws BussinessException;
}
