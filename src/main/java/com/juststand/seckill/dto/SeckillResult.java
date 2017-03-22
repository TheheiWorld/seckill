package com.juststand.seckill.dto;

/**
 * 封装请求返回的json数据
 * Created By juststand on 2017-3-22
 */
public class SeckillResult<T>{
	
	/**
	 * 是否成功
	 */
	private boolean success;
	/**
	 * 返回数据
	 */
	private T data;
	/**
	 * 错误信息
	 */
	private String error;
	public SeckillResult(boolean success, String error) {
		super();
		this.success = success;
		this.error = error;
	}
	public SeckillResult(boolean success, T data) {
		super();
		this.success = success;
		this.data = data;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
}
