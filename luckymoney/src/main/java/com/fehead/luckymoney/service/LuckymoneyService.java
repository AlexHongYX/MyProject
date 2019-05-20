package com.fehead.luckymoney.service;

import com.fehead.luckymoney.domain.Luckymoney;
import com.fehead.luckymoney.properties.LuckymoneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Created by xiaoaxiao on 2019/5/20
 * Description:
 */
@Service
public class LuckymoneyService {

    @Autowired
    private LuckymoneyRepository luckymoneyRepository;

    /**
     * 事务：指数据库的事务（需要将数据库的引擎设置为innoDB）
     * 扣库存+创建订单
     */
    @Transactional
    public void createTwo(){
        Luckymoney luckymoney1 = new Luckymoney();
        luckymoney1.setProducer("A");
        luckymoney1.setMoney(new BigDecimal("520"));
        luckymoneyRepository.save(luckymoney1);

        Luckymoney luckymoney2 = new Luckymoney();
        luckymoney2.setProducer("B");
        luckymoney2.setMoney(new BigDecimal("1314"));
        luckymoneyRepository.save(luckymoney2);
    }
}
