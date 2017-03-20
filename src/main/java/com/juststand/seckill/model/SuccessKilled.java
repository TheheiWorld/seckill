package com.juststand.seckill.model;

import java.util.Date;

/**
 * Created By juststand on 2017-3-20
 */
public class SuccessKilled {
	
	/**
	 * 秒杀商品id
	 */
	private long seckilled;
	/**
	 * 用户手机号码
	 */
	private long userPhone;
	/**
	 * 秒杀状态
	 */
	private short state;
	/**
	 * 创建时间
	 */
	private Date createTime;
	private Seckill seckill;
	
	public long getSeckilled() {
		return seckilled;
	}
	public void setSeckilled(long seckilled) {
		this.seckilled = seckilled;
	}
	public long getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(long userPhone) {
		this.userPhone = userPhone;
	}
	public short getState() {
		return state;
	}
	public void setState(short state) {
		this.state = state;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Seckill getSeckill() {
		return seckill;
	}
	public void setSeckill(Seckill seckill) {
		this.seckill = seckill;
	}
	@Override
	public String toString() {
		return "SuccessKilled [seckilled=" + seckilled + ", userPhone="
				+ userPhone + ", state=" + state + ", createTime=" + createTime
				+ "]";
	}
	
}
