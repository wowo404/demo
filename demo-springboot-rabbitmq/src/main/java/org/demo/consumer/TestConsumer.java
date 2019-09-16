package org.demo.consumer;

import lombok.extern.slf4j.Slf4j;
import org.demo.config.RabbitMQConfig;
import org.demo.service.TestService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestConsumer {

    @Autowired
    private TestService testService;

    @RabbitListener(queues = {RabbitMQConfig.TEST_QUEUE_NAME})
    public void receive(String msg){
        log.info("线程ID是：{}", Thread.currentThread().getId());
        log.info("引用对象是：{}", testService);
        log.info("消息是：{}", msg);
        testService.analysis(msg);
    }

}
