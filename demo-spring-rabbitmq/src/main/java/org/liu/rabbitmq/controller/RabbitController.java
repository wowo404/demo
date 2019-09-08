package org.liu.rabbitmq.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitController {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${mq.direct.exchange}")
    private String directExchange;
    @Value("${mq.routingkey.command}")
    private String routingKeyCommand;

    @GetMapping("send")
    public String send(String msg){
        rabbitTemplate.convertAndSend(directExchange, routingKeyCommand, msg);
        return "success";
    }

}
