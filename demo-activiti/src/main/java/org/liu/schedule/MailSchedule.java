package org.liu.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 告警及错误信息邮件发送定时任务
 * @author liuzhsh
 */
@Slf4j
@Component
public class MailSchedule {

	@Autowired
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;
	
	/**
	 * 每20秒触发
	 */
	@Scheduled(cron = "*/20 * * * * ?")
	public void execute(){
		log.info("start time:{}", System.currentTimeMillis());
		for (int i = 0; i < 10; i++) {
			log.info("from execute method,thread name:{}", Thread.currentThread().getName());
			threadPoolTaskExecutor.execute(new SendMailToAdminTask(i));
		}
		log.info("end time:{}", System.currentTimeMillis());
	}
	
	public class SendMailToAdminTask implements Runnable {

		private int times;
		public SendMailToAdminTask(int times){
			this.times = times;
		}
		
		@Override
		public void run() {
			if(times % 2 == 0){
				try {
					Thread.sleep(10*1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			log.info("from Runnable run method,thread name:{},times:{}",Thread.currentThread().getName(),times);
		}
		
	}
	
}
