package com.juststand.seckill.exception;

/**
 * 秒杀关闭异常
 * Created By juststand on 2017-3-21
 */
public class SeckillCloseException extends SeckillException {
	private static final long serialVersionUID = 1L;

	public SeckillCloseException(String message, Throwable cause) {
		super(message, cause);
	}

	public SeckillCloseException(String message) {
		super(message);
	}
	
}
