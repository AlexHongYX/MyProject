package com.fehead.luckymoney.service;

import com.fehead.luckymoney.domain.Luckymoney;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by xiaoaxiao on 2019/5/21
 * Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LuckymoneyServiceTest {

    @Autowired
    private LuckymoneyService luckymoneyService;

    @Test
    public void findOne() {
//        Luckymoney luckymoney = luckymoneyService.findOne(5);
//        System.out.println(luckymoney);
    }
}