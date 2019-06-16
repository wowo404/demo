package org.activemq.test;

import java.io.IOException;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;

import org.apache.activemq.BlobMessage;

public class CustomMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message) {

		try {
			if(message instanceof BytesMessage){
				StringBuilder sb = new StringBuilder();
				BytesMessage bytesMsg = (BytesMessage) message;
				byte[] temp = new byte[1024];
				int length = -1;
				while((length = bytesMsg.readBytes(temp)) != -1){
					sb.append(new String(temp, 0, length));
				}
				System.out.println("Consumer接收到BytesMessage内容为：" + sb.toString());
			} else if(message instanceof MapMessage){
				MapMessage mapMsg = (MapMessage) message;
				byte b = mapMsg.getByteProperty("byte");
				double d = mapMsg.getDoubleProperty("double");
				float f = mapMsg.getFloatProperty("float");
				int i = mapMsg.getIntProperty("int");
				long l = mapMsg.getLongProperty("long");
				Object o = mapMsg.getObjectProperty("object");
				short s = mapMsg.getShortProperty("short");
				String str = mapMsg.getStringProperty("string");
				String format = String.format("byte:%s,double:%s,float:%s,int:%s,long:%s,object:%s,short:%s,string:%s", b,d,f,i,l,o,s,str);
				System.out.println(format);
				System.out.println("Consumer接收到MapMessage内容为：" + mapMsg);
			} else if(message instanceof BlobMessage){//activemq对jms的扩展
				BlobMessage blobMsg = (BlobMessage) message;
				blobMsg.getInputStream();
				System.out.println("Consumer接收到BlobMessage内容为：未打印的InputStream");
			} else if(message instanceof ObjectMessage){
				ObjectMessage objMsg = (ObjectMessage) message;
				System.out.println("Consumer接收到ObjectMessage内容为：" + objMsg.getObject());
			} else if(message instanceof StreamMessage){
				StreamMessage streamMsg = (StreamMessage) message;
				System.out.println("Consumer接收到StreamMessage内容为：" + streamMsg.readString());
			} else if(message instanceof TextMessage){
				TextMessage textMsg = (TextMessage) message;
				System.out.println("Consumer接收到TextMessage内容为：" + textMsg.getText());
			}
		} catch (JMSException | IOException e) {
			e.printStackTrace();
		}

	}

}
