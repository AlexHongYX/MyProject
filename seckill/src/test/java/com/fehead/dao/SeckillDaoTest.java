package com.fehead.dao;

import com.fehead.bean.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 配置spring和junit整合，junit启动时会加载springIOC容器
 * spring-test，junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring的配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest{

    //注入Dao实现类依赖
    @Resource
    private SeckillDao seckillDao;


    @Test
    public void queryById() {
        long id =1000;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }

    @Test
    /**
     * Java没有保存形参的记录：queryAll(int offet,int limit)->queryAll(arg0,arg1)
     */
    public void queryAll() {
        List<Seckill> seckills = seckillDao.queryAll(0,100);
        for(Seckill seckill:seckills){
            System.out.println(seckill);
        }
    }

    @Test
    public void reduceNumber() {
        Date killTime = new Date();
        int updateCount = seckillDao.reduceNumber(1000L,killTime);
        //因为现在时间已经过了，所以不会再更新，所以updateCount返回0
        System.out.println("updateCount:"+updateCount);
    }


}