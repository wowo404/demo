package org.liu.rabbitmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.liu.rabbitmq.model.ReceiveMsgDTO;

@Slf4j
public class RabbitConsumer {

    /**
     * 消息类型是json格式，如果要用对象接受，请求头中要设置__TypeId__=org.liu.rabbitmq.model.ReceiveMsgDTO（要全路径名）
     * 这个就比较死板，不灵活
     */
    public void consume(ReceiveMsgDTO msg){
        log.info("receive message:{}", msg);
    }

    /**
     * 如果请求的properties中没有设置contentType为application/json，就会使用默认的byte[]接收
     */
    public void consume(byte[] msg){
        log.info("receive byte message:{}", new String(msg));
    }

}
