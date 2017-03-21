package com.juststand.seckill.exception;

/**
 * 重复秒杀异常
 * Created By juststand on 2017-3-21
 */
public class RepeatKillException extends SeckillException {
	private static final long serialVersionUID = 1L;

	public RepeatKillException(String message, Throwable cause) {
		super(message, cause);
	}

	public RepeatKillException(String message) {
		super(message);
	}
	
}
