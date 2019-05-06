package com.fehead.dao;

import com.fehead.bean.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SeckillDao {

    /**
     * 减库存
     * @param seckillId
     * @param killTime
     * @return 返回值的效果：如果影响行数>1，表示更新的记录行数（更新影响几行）
     */
    int reduceNumber(@Param("seckillId") long seckillId,@Param("killTime") Date killTime);

    /**
     * 根据id查询秒杀对象
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);

    /**
     * 根据偏移量查询秒杀商品列表
     * @param offet
     * @param limit
     * @return
     */
    /**
     * Java没有保存形参的记录：queryAll(int offet,int limit)->queryAll(arg0,arg1)
     * 所以使用MyBatis提供的@Param来实现对多个参数的访问
     */
    List<Seckill> queryAll(@Param("offet") int offet, @Param("limit") int limit);

    /**
     * 使用存储过程执行秒杀
     * @param paramMap
     */
    void killByProcedure(Map<String,Object> paramMap);
}
