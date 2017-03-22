package com.juststand.seckill.dao;

import org.apache.ibatis.annotations.Param;
import com.juststand.seckill.model.SuccessKilled;

/**
 * Created By juststand on 2017-3-20
 */
public interface SuccessKilledDao {
	
	/**
	 * 储存用户秒杀单，联合主键可过滤重复
	 * @param seckillId
	 * @param userPhone
	 * @return
	 */
	int saveSuccessKilled (@Param("seckillId")long seckillId ,@Param("userPhone")long userPhone);
	/**
	 * 根据商品ID查询秒杀成功的，并显示秒杀商品
	 * @param seckillId
	 * @return
	 */
	SuccessKilled getByIdWithSeckill (@Param("seckillId")long seckillId ,@Param("userPhone")long userPhone);
}
