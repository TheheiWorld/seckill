package com.juststand.seckill.service;

import java.util.List;

import com.juststand.seckill.dto.Exposer;
import com.juststand.seckill.dto.SeckillExecution;
import com.juststand.seckill.exception.RepeatKillException;
import com.juststand.seckill.exception.SeckillCloseException;
import com.juststand.seckill.exception.SeckillException;
import com.juststand.seckill.model.Seckill;

/**
 * 业务接口
 * Created By juststand on 2017-3-21
 */
public interface SeckillService {
	
	/**
	 * 查询所有的秒杀商品
	 * @return
	 */
	List<Seckill> getSeckills ();
	
	/**
	 * 查询单个秒杀
	 * @param seckillId
	 * @return
	 */
	Seckill getById (long seckillId);
	
	/**
	 * 秒杀开启时输出秒杀接口地址，否则输出系统时间和秒杀时间
	 * @param seckillId
	 */
	Exposer exportSeckillUrl (long seckillId);
	
	/**
	 * 执行秒杀
	 * @param seckillId
	 * @param userPhone
	 * @param md5 用户请求传递过来的
	 */
	SeckillExecution executeSeckill (long seckillId ,long userPhone ,String md5)
			throws SeckillException,SeckillCloseException,RepeatKillException;
}
