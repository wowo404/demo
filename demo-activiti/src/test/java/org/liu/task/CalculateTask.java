package org.liu.task;

import java.util.concurrent.Callable;

import org.joda.time.LocalDateTime;

public class CalculateTask implements Callable<Void> {

	private LocalDateTime now;
	private Integer times;
	
	public CalculateTask(LocalDateTime now, Integer times){
		this.now = now;
		this.times = times;
	}
	
	@Override
	public Void call() throws Exception {
		
		System.out.println(times + "---" + now.toString());
		
		Thread.sleep(10*1000);
		
		return null;
	}

}
