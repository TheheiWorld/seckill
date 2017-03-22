package com.juststand.seckill.dao;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.juststand.seckill.model.Seckill;

/**
 * Created By juststand on 2017-3-20
 */
public interface SeckillDao {
	
	/**
	 * 减库存 
	 * 注解参数的目的是java没有保存形参的记录
	 * @param seckillId
	 * @param killTime
	 * @return
	 */
	int reduceNumber (@Param("seckillId") long seckillId ,@Param("killTime")Date killTime);
	
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
	List<Seckill> getAll (@Param("offset")int offset , @Param("limit")int limit);

}
