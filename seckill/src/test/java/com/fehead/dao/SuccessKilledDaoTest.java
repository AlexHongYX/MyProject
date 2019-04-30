package com.fehead.dao;

import com.fehead.bean.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * 配置spring和junit整合，junit启动时会加载springIOC容器
 * spring-test，junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring的配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest{

    @Resource
    private SuccessKilledDao successKilledDao;
    @Test
    public void insertSuccessKilled(){
        long id = 1000L;
        long phone = 15114875412L;
        int insertCount = successKilledDao.insertSuccessKilled(id,phone);
        System.out.println("insertCount:"+insertCount);
    }

    @Test
    public void queryByIdWithSeckill() {
        long id = 1000L;
        long phone = 15114875412L;
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(id,phone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }
}