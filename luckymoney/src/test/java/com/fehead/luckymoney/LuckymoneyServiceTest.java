package com.fehead.luckymoney;

import com.fehead.luckymoney.domain.Luckymoney;
import com.fehead.luckymoney.service.LuckymoneyService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * Created by xiaoaxiao on 2019/5/21
 * Description:LuckymoneyService的测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LuckymoneyServiceTest {

    @Autowired
    private LuckymoneyService luckymoneyService;

    @Test
    public void findOneTest(){
        Luckymoney luckymoney = luckymoneyService.findOne(5);
//        Assert.assertEquals(new BigDecimal(1314),luckymoney.getMoney());
        System.out.println(luckymoney);
    }
}
