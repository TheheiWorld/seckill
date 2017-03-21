package com.juststand.seckill.dto;

import com.juststand.seckill.enums.SeckillStateEnum;
import com.juststand.seckill.model.SuccessKilled;

/**
 * 封装执行秒杀后的结果
 * Created By juststand on 2017-3-21
 */
public class SeckillExecution {
	
	/**
	 * 秒杀商品
	 */
	private long seckillId;
	/**
	 * 执行秒杀的结果
	 */
	private int state;
	/**
	 * 状态表示
	 */
	private String stateInfo;
	/**
	 * 秒杀成功对象
	 */
	private SuccessKilled successKilled;
	
	public SeckillExecution(long seckillId, SeckillStateEnum seckillStateEnum) {
		super();
		this.seckillId = seckillId;
		this.state = seckillStateEnum.getState();
		this.stateInfo = seckillStateEnum.getStateInfo();
	}
	public SeckillExecution(long seckillId, SeckillStateEnum seckillStateEnum,
			SuccessKilled successKilled) {
		super();
		this.seckillId = seckillId;
		this.state = seckillStateEnum.getState();
		this.stateInfo = seckillStateEnum.getStateInfo();
		this.successKilled = successKilled;
	}
	public long getSeckillId() {
		return seckillId;
	}
	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getStateInfo() {
		return stateInfo;
	}
	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}
	public SuccessKilled getSuccessKilled() {
		return successKilled;
	}
	public void setSuccessKilled(SuccessKilled successKilled) {
		this.successKilled = successKilled;
	}
	@Override
	public String toString() {
		return "SeckillExecution [seckillId=" + seckillId + ", state=" + state
				+ ", stateInfo=" + stateInfo + ", successKilled="
				+ successKilled + "]";
	}
	
}
