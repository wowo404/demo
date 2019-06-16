package org.activemq.test.pool;

import javax.jms.Message;

/**
 * 提供消息操作的回调接口
 */
public interface MessageHandler {

	/**
	 * 消息回调提供的调用方法
	 */
	public void handle(Message message);
	
}
