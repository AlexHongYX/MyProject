package com.fehead.service.Impl;

import com.fehead.dao.PromoInfoMapper;
import com.fehead.dataobject.PromoInfo;
import com.fehead.service.PromoService;
import com.fehead.service.model.PromoModel;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by xiaoaxiao on 2019/5/13
 * Description:
 */
@Service
public class PromoServiceImpl implements PromoService {

    @Autowired
    private PromoInfoMapper promoInfoMapper;

    @Override
    public PromoModel getPromoByItemId(Integer itemId) {

        //获取对应商品的秒杀信息
        PromoInfo promoInfo = promoInfoMapper.selectByItemId(itemId);

        //dateObject->Model
        PromoModel promoModel = convertPromoModelFromPromoInfo(promoInfo);

        //判断一下promoModel是否为null，为null直接返回，不为null再进行判断
        if(promoModel!=null){
            //判断当前时间是否秒杀活动即将开始或正在进行（确定秒杀的状态）
            //isAfterNow->判断startDate是否在nowDate之后
            //isBeforeNow->判断endDate是否在nowDate之前
            if(promoModel.getStartDate().isAfterNow()){     //startDate在nowDate之后，还未开始（status=1）
                promoModel.setStatus(1);
            }else if(promoModel.getEndDate().isBeforeNow()){    //endDate在nowDate之前，已结束（status=3）
                promoModel.setStatus(3);
            }else {     //活动正在进行中（status=2）
                promoModel.setStatus(2);
            }
        }

        //返回带有秒杀状态的promoModel或者是没有该秒杀返回null
        return promoModel;
    }

    private PromoModel convertPromoModelFromPromoInfo(PromoInfo promoInfo){
        if(promoInfo==null){
            return null;
        }
        PromoModel promoModel = new PromoModel();
        BeanUtils.copyProperties(promoInfo,promoModel);
        //double->BigDecimal
        promoModel.setPromoItemPrice(new BigDecimal(promoInfo.getPromoItemPrice()));
        //Date->DateTime
        promoModel.setStartDate(new DateTime(promoInfo.getStartDate()));
        promoModel.setEndDate(new DateTime(promoInfo.getEndDate()));
        return promoModel;
    }
}
