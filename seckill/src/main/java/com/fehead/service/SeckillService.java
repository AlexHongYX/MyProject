package com.fehead.service;

import com.fehead.bean.Seckill;
import com.fehead.dto.Exposer;
import com.fehead.dto.SeckillExecution;
import com.fehead.exception.RepeatKillException;
import com.fehead.exception.SeckillCloseException;
import com.fehead.exception.SeckillException;

import javax.swing.text.html.parser.Entity;
import java.util.List;

/**
 * Created by xiaoaxiao on 2019/5/3
 * Description:业务接口：站在“使用者”角度设计接口
 *             三个方面：方法定义粒度，参数，返回类型（return 类型/异常）
 */
public interface SeckillService {

    /**
     * 查询所有秒杀记录
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀记录
     * @param seckillId
     * @return
     */
    Seckill getSeckillById(long seckillId);



    /**
     * 秒杀开启时输出秒杀接口地址，秒杀存在但未开启时输出系统时间和秒杀时间，秒杀不存在直接返回id就行
     * ——这可以通过创建多个Exposer的构造器来实现
     * 当web层或接口使用方调用该方法时，可以拿到Exposer的dto，可以看到一些返回的数据
     * @param seckillId
     * @return
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
        throws SeckillException, SeckillCloseException, RepeatKillException;
    //抛出SeckillCloseException, RepeatKillException异常需要明确告诉用户或接口使用者，秒杀关闭异常/重复秒杀异常
    //而抛出SeckillException异常只需要告诉用户秒杀存在异常，秒杀失败
}
