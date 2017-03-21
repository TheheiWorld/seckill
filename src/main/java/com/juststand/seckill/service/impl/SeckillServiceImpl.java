package com.juststand.seckill.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.juststand.seckill.dao.SeckillDao;
import com.juststand.seckill.dao.SuccessKilledDao;
import com.juststand.seckill.dto.Exposer;
import com.juststand.seckill.dto.SeckillExecution;
import com.juststand.seckill.enums.SeckillStateEnum;
import com.juststand.seckill.exception.RepeatKillException;
import com.juststand.seckill.exception.SeckillCloseException;
import com.juststand.seckill.exception.SeckillException;
import com.juststand.seckill.model.Seckill;
import com.juststand.seckill.model.SuccessKilled;
import com.juststand.seckill.service.SeckillService;

@Service
public class SeckillServiceImpl implements SeckillService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SeckillDao seckillDao;
	
	@Autowired
	private SuccessKilledDao successKilledDao;
	/**
	 * 盐值
	 */
	private final String salt ="AWXFDCFXGFGBHYRDD";
	
	@Override
	public List<Seckill> getSeckills() {
		return seckillDao.getAll(0, 5);
	}

	@Override
	public Seckill getById(long seckillId) {
		return seckillDao.getById(seckillId);
	}

	@Override
	public Exposer exportSeckillUrl(long seckillId) {
		Seckill seckill = seckillDao.getById(seckillId);
		if (seckill == null) {
			return new Exposer(false, seckillId);
		}
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		Date nowTime = new Date();
		
		if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
			return new Exposer(false, seckillId, nowTime.getTime(),startTime.getTime(), endTime.getTime());
		}
		//加密
		String md5 = getMD5(seckillId);
		return new Exposer(true, md5 , seckillId);
	}
	
	
	private String getMD5(long seckillId) {
		String base = seckillId + "/" + salt;
		return DigestUtils.md5DigestAsHex(base.getBytes());	
	}

	@Override
	@Transactional
	public SeckillExecution executeSeckill(long seckillId, long userPhone,
			String md5) throws SeckillException, SeckillCloseException,
			RepeatKillException {
		if (md5 == null || !this.getMD5(seckillId).equals(md5)) {
			throw new SeckillException("seckill data rewrite");
		}
		//秒杀逻辑：减少库存，记录秒杀行为
		Date killTime = new Date();
		try {
			int reduceNumber = seckillDao.reduceNumber(seckillId, killTime);
			if (reduceNumber <= 0) {
				//库存不足 或者 时间过了
				throw new SeckillCloseException("seckill closed");
			} else {
				int saveSuccessKilled = successKilledDao.saveSuccessKilled(seckillId, userPhone);
				//重复秒杀
				if (saveSuccessKilled <= 0) {
					throw new RepeatKillException("seckill repeated");
				} else {
					//秒杀成功
					SuccessKilled successKilled = successKilledDao.getByIdWithSeckill(seckillId, userPhone);
					return new SeckillExecution(seckillId,SeckillStateEnum.SUCCESS,successKilled);
				}
			}
		}catch(SeckillCloseException seckillCloseException){
			throw seckillCloseException;
		}catch (RepeatKillException repeatKillException) {
			throw repeatKillException;
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
			//编译器异常 转换为运行期异常，spring 声明式事务 会执行回滚操作
			throw new SeckillException("seckill inner error" + e.getMessage());
		}
	}
}
