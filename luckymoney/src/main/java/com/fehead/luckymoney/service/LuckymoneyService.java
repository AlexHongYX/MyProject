package com.fehead.luckymoney.service;

import com.fehead.luckymoney.domain.Luckymoney;
import com.fehead.luckymoney.domain.Result;
import com.fehead.luckymoney.enums.ResultEnum;
import com.fehead.luckymoney.exception.LuckymoneyException;
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

    public void getMoney(Integer id) throws Exception {
        //2.0版本需要luckymoneyRepository.findById(id).orElse(null);
        Luckymoney luckymoney = luckymoneyRepository.findById(id).orElse(null);
        BigDecimal money = luckymoney.getMoney();
        if(money.intValue()<10){
            //"这么点钱能够？"
            throw new LuckymoneyException(ResultEnum.UNDER_TEN);
        }else if(money.intValue()>=10&&money.intValue()<=20){
            //"就加10块钱？"
            throw new LuckymoneyException(ResultEnum.UNDER_TWENTY);
        }
    }

    /**
     * 通过id查询luckymoney——做测试用
     */
    public Luckymoney findOne(Integer id){
        return luckymoneyRepository.findById(id).orElse(null);
    }
}
