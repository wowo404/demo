package org.activemq.test.pool;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;

public class PooledProducer implements ExceptionListener {

	// 设置连接的最大连接数
	private int maxConnections = DEFAULT_MAX_CONNECTIONS;
	public static final int DEFAULT_MAX_CONNECTIONS = 10;
	// 设置每个连接中使用的最大活动会话数
	private int maximumActiveSessionPerConnection = DEFAULT_MAXIMUM_ACTIVE_SESSION_PER_CONNECTION;
	public static final int DEFAULT_MAXIMUM_ACTIVE_SESSION_PER_CONNECTION = 300;
	// 线程池数量
	private int threadPoolSize = DEFAULT_THREAD_POOL_SIZE;
	public static final int DEFAULT_THREAD_POOL_SIZE = 50;
	// 使用异步发送
	//If you are not using transactions and are sending persistent messages, 
	//then each send is synch and blocks until the broker has sent back an acknowledgement to the producer that the message has been safely persisted to disk.
	private boolean useAsyncSendForJMS = DEFAULT_USE_ASYNC_SEND_FOR_JMS;
	public static final boolean DEFAULT_USE_ASYNC_SEND_FOR_JMS = true;
	// 是否持久化消息
	private boolean isPersistent = DEFAULT_IS_PERSISTENT;
	public final static boolean DEFAULT_IS_PERSISTENT = true;

	// 连接地址
	private String brokerUrl;
	private String userName;
	private String password;
	private ExecutorService threadPool;
	private PooledConnectionFactory connectionFactory;

	public PooledProducer(String brokerUrl, String userName, String password) {
		this(brokerUrl, userName, password, DEFAULT_MAX_CONNECTIONS, DEFAULT_MAXIMUM_ACTIVE_SESSION_PER_CONNECTION,
				DEFAULT_THREAD_POOL_SIZE, DEFAULT_USE_ASYNC_SEND_FOR_JMS, DEFAULT_IS_PERSISTENT);
	}

	public PooledProducer(String brokerUrl, String userName, String password, int maxConnections,
			int maximumActiveSessionPerConnection, int threadPoolSize, boolean useAsyncSendForJMS,
			boolean isPersistent) {
		this.brokerUrl = brokerUrl;
		this.userName = userName;
		this.password = password;
		this.maxConnections = maxConnections;
		this.maximumActiveSessionPerConnection = maximumActiveSessionPerConnection;
		this.threadPoolSize = threadPoolSize;
		this.useAsyncSendForJMS = useAsyncSendForJMS;
		this.isPersistent = isPersistent;
		init();
	}

	private void init() {
		this.threadPool = Executors.newFixedThreadPool(threadPoolSize);
		ActiveMQConnectionFactory actualConnectionFactory = new ActiveMQConnectionFactory(userName, password, brokerUrl);
		actualConnectionFactory.setUseAsyncSend(useAsyncSendForJMS);
		this.connectionFactory = new PooledConnectionFactory(actualConnectionFactory);
		this.connectionFactory.setMaxConnections(maxConnections);
		this.connectionFactory.setMaximumActiveSessionPerConnection(maximumActiveSessionPerConnection);
	}

	public void send(final String queueName, Map<String, Object> messageMap){
		this.threadPool.execute(new Runnable(){

			@Override
			public void run() {
				try {
					System.out.println(Thread.currentThread().getName());
					sendMessage(queueName, messageMap);
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}}
		
		);
	}
	
	protected void sendMessage(String queueName, Map<String, Object> messageMap) throws JMSException {
		Connection connection = null;
		Session session = null;
		try {
			connection = this.connectionFactory.createQueueConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue(queueName);
			MessageProducer producer = session.createProducer(destination); 
			Message message = transfer(session, messageMap);
			producer.setDeliveryMode(this.isPersistent ? DeliveryMode.PERSISTENT : DeliveryMode.NON_PERSISTENT);
			producer.send(message);
		} finally {
			closeSession(session);
			closeConnection(connection);
		}
	}

	private void closeConnection(Connection connection) throws JMSException {
		if(null != connection){
			connection.close();
		}
	}

	private void closeSession(Session session) throws JMSException {
		if(null != session){
			session.close();
		}
	}

	private Message transfer(Session session, Map<String, Object> messageMap) throws JMSException {
		MapMessage message = session.createMapMessage();
		Set<Entry<String, Object>> set = messageMap.entrySet();
		for (Entry<String, Object> entry : set) {
			message.setObject(entry.getKey(), entry.getValue());
		}
		return message;
	}

	@Override
	public void onException(JMSException exception) {
		exception.printStackTrace();
	}

}
