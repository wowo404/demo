package org.demo.controller;

import org.demo.config.RabbitMQConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping("send")
    public String send(String msg) throws InterruptedException {
        for(int i = 0; i < 10; i++){
            amqpTemplate.convertAndSend(RabbitMQConfig.TEST_QUEUE_NAME, msg + i);
            Thread.sleep(1000);
        }
        return "success";
    }

}
