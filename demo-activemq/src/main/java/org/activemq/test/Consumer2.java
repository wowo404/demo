package org.activemq.test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer2 {

	private String username = "system";
	private String password = "manager";
	private static String brokerURL = "tcp://192.168.137.5:61616";
	private static transient ConnectionFactory factory;
	private transient Connection connection;
	private transient Session session;

	public Session getSession() {
		return session;
	}

	public Consumer2() throws JMSException {
		factory = new ActiveMQConnectionFactory(username, password, brokerURL);
		connection = factory.createConnection();
		connection.start();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	}

	public void close() throws JMSException {
		if (connection != null) {
			connection.close();
		}
	}

	public static void main(String[] args) throws JMSException {
		Consumer2 consumer = new Consumer2();
		Destination destination = consumer.getSession().createTopic("test.orgin");
		MessageConsumer messageConsumer = consumer.getSession().createConsumer(destination);
		messageConsumer.setMessageListener(new CustomMessageListener());
	}

}
