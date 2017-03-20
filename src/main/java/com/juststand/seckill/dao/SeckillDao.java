package com.juststand.seckill.dao;

import java.util.Date;
import java.util.List;

import com.juststand.seckill.model.Seckill;

/**
 * Created By juststand on 2017-3-20
 */
public interface SeckillDao {
	
	/**
	 * 减库存
	 * @param seckillId
	 * @param killTime
	 * @return
	 */
	int reduceNumber (long seckillId ,Date killTime);
	
	/**
	 * 通过seckillId 查找
	 * @param seckillId
	 * @return
	 */
	Seckill getById (long seckillId);
	
	/**
	 * 根据偏移量查询秒杀商品列表
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<Seckill> getAll (int offset , int limit);

}
