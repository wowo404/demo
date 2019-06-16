package org.activemq.test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Producer {

	private String username = "system";
	private String password = "manager";
	private static String brokerURL = "tcp://192.168.137.5:61616";
	private static transient ConnectionFactory factory;
	private transient Connection connection;
	private transient Session session;
	private transient MessageProducer producer;

	public Producer() throws JMSException {
		factory = new ActiveMQConnectionFactory(username, password, brokerURL);
		connection = factory.createConnection();
		connection.start();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		producer = session.createProducer(null);
	}

	public void close() throws JMSException {
		if (null != connection) {
			connection.close();
		}
	}

	public void sendMessage(int i) throws JMSException {
		Topic topic = session.createTopic("test.orgin");
		Message message = session.createMapMessage();
		message.setBooleanProperty("boolean", false);
		byte bt = 44;
		message.setByteProperty("byte", bt);
		message.setDoubleProperty("double", 41.76);
		message.setFloatProperty("float", 23f);
		message.setIntProperty("int", i);
		message.setLongProperty("long", 7788823);
		//Object指的是基础类型对应的包装类和map，list
		message.setObjectProperty("Character", "s");
		message.setShortProperty("short", Short.MAX_VALUE);
		message.setStringProperty("string", "from string");
		producer.send(topic, message);
	}

	public static void main(String[] args) throws JMSException {
		Producer producer = new Producer();
		for (int i = 0; i < 100; i++) {
			producer.sendMessage(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException x) {
			}
		}
		producer.close();
	}

}
