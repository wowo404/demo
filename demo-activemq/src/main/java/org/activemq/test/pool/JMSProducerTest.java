package org.activemq.test.pool;

import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;

public class JMSProducerTest {

	public static void main(String[] args) throws JMSException {

		locationTest();
		System.out.println("over.");
	}

	private static void locationTest() throws JMSException {
		// ** JMSProducer
		// 可以设置成全局的静态变量，只需实例化一次即可使用,禁止循环重复实例化JMSProducer(因为其内部存在一个线程池)

		// 支持openwire协议的默认连接为 tcp://localhost:61616，支持
		// stomp协议的默认连接为tcp://localhost:61613。
		// tcp和nio的区别
		// nio://localhost:61617 以及 tcp://localhost:61616均可在
		// activemq.xml配置文件中进行配置
		PooledProducer producer = new PooledProducer("auto+nio://192.168.137.5:61608", "system", "manager");
		for (int i = 0; i < 100; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", i);
			map.put("name", "name" + i);
			map.put("password", "password");
			producer.send("test", map);
		}
//		producer.close();
	}

}
