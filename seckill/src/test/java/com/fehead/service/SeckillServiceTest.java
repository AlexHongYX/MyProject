package com.fehead.service;

import com.fehead.bean.Seckill;
import com.fehead.dto.Exposer;
import com.fehead.dto.SeckillExecution;
import com.fehead.exception.RepeatKillException;
import com.fehead.exception.SeckillCloseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by xiaoaxiao on 2019/5/3
 * Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() {
        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list={}", list);
    }


    @Test
    public void getSeckillById() {
        long id = 1000;
        Seckill seckill = seckillService.getSeckillById(id);
        logger.info("seckill={}",seckill);
    }

    @Test
    /**
     * Exposer{
     * exposed=true,
     * md5='fe95f6f9363d3286ddcb4f93e6082a4c',
     * seckillId=1000,
     * now=0, start=0, end=0}
     */
    public void exportSeckillUrl() {
        long id = 1000;
        //修改数据库把活动开启时间改为2019.5.1-2019.11.29
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("exposer={}",exposer);
    }

    @Test
    /**
     * SeckillExecution{seckillId=1000, state=1, stateInfo='秒杀成功',
     * successKilled=SuccessKilled{seckillId=1000, userPhone=12345678911,
     * state=0, createTime=Fri May 03 22:18:31 CST 2019, seckill=Seckill{
     * seckillId=1000, name='1000元秒杀iphone6', number=99, startTime=Fri May 03 22:18:31 CST 2019,
     * endTime=Sat Nov 02 00:00:00 CST 2019, createTime=Mon Apr 29 16:46:23 CST 2019}}}
     */
    public void executeSeckill() {
        long id = 1000;
        long phone = 12345678911L;
        String md5 = "fe95f6f9363d3286ddcb4f93e6082a4c";
        //通过try-catch对已定义好了的异常进行相应的处理，就不会乱报错了
        try {
            SeckillExecution seckillExecution = seckillService.executeSeckill(id,phone,md5);
            logger.info("result={}",seckillExecution);
        }catch (RepeatKillException e){
            logger.error(e.getMessage());
        }catch (SeckillCloseException e){
            logger.error(e.getMessage());
        }
    }

    @Test
    /**
     * 集成测试代码完整逻辑，注意可重复执行——上面两个方法加起来，直接使用md5，而不是复制
     */
    public void testSeckillLogic() throws Exception{
        long id = 1001;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if(exposer.isExposed()){
            logger.info("exposer={}",exposer);
            long phone = 12345678911L;
            String md5 = "fe95f6f9363d3286ddcb4f93e6082a4c";
            //通过try-catch对已定义好了的异常进行相应的处理，就不会乱报错了
            try {
                SeckillExecution seckillExecution = seckillService.executeSeckill(id,phone,md5);
                logger.info("result={}",seckillExecution);
            }catch (RepeatKillException e){
                logger.error(e.getMessage());
            }catch (SeckillCloseException e){
                logger.error(e.getMessage());
            }
        }else {
            //秒杀未开启
            logger.warn("exposer={}",exposer);
        }
    }
}