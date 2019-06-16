package org.activemq.test.pool;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.QueueConnection;
import javax.jms.QueueSession;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQPrefetchPolicy;

public class PooledConsumer implements ExceptionListener {

	// 队列预取策略
	private int queuePrefetch = DEFAULT_QUEUE_PREFETCH;
	public final static int DEFAULT_QUEUE_PREFETCH = 100;

	private String brokerUrl;
	private String userName;
	private String password;
	private MessageListener messageListener;
	private Connection connection;
	private Session session;
	// 队列名
	private String queue;

	public PooledConsumer(String brokerUrl, String userName, String password) {
		this(brokerUrl, userName, password, DEFAULT_QUEUE_PREFETCH);
	}

	public PooledConsumer(String brokerUrl, String userName, String password, int queuePrefetch) {
		this.brokerUrl = brokerUrl;
		this.userName = userName;
		this.password = password;
		this.queuePrefetch = queuePrefetch;
	}

	public void start() throws JMSException {
		// ActiveMQ的连接工厂
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(this.userName, this.password,
				this.brokerUrl);
		connection = connectionFactory.createConnection();
		// activeMQ预取策略
		ActiveMQPrefetchPolicy prefetchPolicy = new ActiveMQPrefetchPolicy();
		prefetchPolicy.setQueuePrefetch(queuePrefetch);
		((ActiveMQConnection) connection).setPrefetchPolicy(prefetchPolicy);
		connection.setExceptionListener(this);
		connection.start();
		// 会话采用非事务级别，消息到达机制使用自动通知机制
		session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue(this.queue);
		MessageConsumer consumer = session.createConsumer(destination);
		consumer.setMessageListener(this.messageListener);
	}

	public void shutdown() throws JMSException {
		if (session != null) {
			session.close();
		}
		if (connection != null) {
			connection.close();
		}
	}

	@Override
	public void onException(JMSException exception) {
		exception.printStackTrace();
	}

	public MessageListener getMessageListener() {
		return messageListener;
	}

	public void setMessageListener(MessageListener messageListener) {
		this.messageListener = messageListener;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(QueueConnection connection) {
		this.connection = connection;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(QueueSession session) {
		this.session = session;
	}

	public String getQueue() {
		return queue;
	}

	public void setQueue(String queue) {
		this.queue = queue;
	}

}
