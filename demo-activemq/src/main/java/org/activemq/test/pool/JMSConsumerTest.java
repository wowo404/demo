package org.activemq.test.pool;

import javax.jms.MapMessage;
import javax.jms.Message;

public class JMSConsumerTest {

	public static void main(String[] args) throws Exception {

		// ** JMSConsumer
		// 可以设置成全局的静态变量，只需实例化一次即可使用,禁止循环重复实例化JMSConsumer(因为其内部存在一个线程池)

		PooledConsumer consumer = new PooledConsumer("auto+nio://192.168.137.5:61608","system","manager");
		consumer.setQueue("test");
		consumer.setMessageListener(new MultiThreadMessageListener(50, new MessageHandler() {
			public void handle(Message message) {
				try {
					System.out.println("name is " + ((MapMessage) message).getString("name"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}));
		consumer.start();

		// Thread.sleep(5000);
		// consumer.shutdown();

	}

}
