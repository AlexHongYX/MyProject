package com.fehead.service;

import com.fehead.service.model.PromoModel;

/**
 * Created by xiaoaxiao on 2019/5/13
 * Description:
 */
public interface PromoService {

    //根据itemId获取即将进行的或正在进行的秒杀活动
    PromoModel getPromoByItemId(Integer itemId);
}
