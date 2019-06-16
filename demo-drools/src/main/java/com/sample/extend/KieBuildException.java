package com.sample.extend;

import lombok.Getter;
import lombok.Setter;

/**
 * drools runtime exception defined
 * @author liuzhsh
 */
public class KieBuildException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	private String message;
	
	public KieBuildException(){
		super();
	}
	
	public KieBuildException(String message){
		this.message = message;
	}
	
	public KieBuildException(String message, Throwable cause){
		super(message, cause);
	}
	
	public KieBuildException(Throwable cause){
		super(cause);
	}
	
	public KieBuildException(String message, Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace){
		super(message,cause,enableSuppression,writableStackTrace);
	}
	
}
