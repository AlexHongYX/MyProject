package com.fehead.dao;

import com.fehead.bean.SuccessKilled;

public interface SuccessKilledDao {

    /**
     * 插入购买明细，可过滤重复
     * @param seckillId
     * @param userPhone
     * @return 插入的行数，可能因为某些冲突返回0表示插入失败
     */
    int insertSuccessKilled(long seckillId,long userPhone);

    /**
     * 根据id查询SuccessKilled并携带秒杀产品对象实体
     * @param seckillId
     * @return 查询的同时携带Seckill的对象
     */
    SuccessKilled queryByIdWithSeckill(long seckillId);
}
