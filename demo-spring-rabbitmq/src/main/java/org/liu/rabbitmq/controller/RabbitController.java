package org.liu.rabbitmq.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

@RestController
public class RabbitController {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${mq.direct.exchange}")
    private String directExchange;
    @Value("${mq.routingkey.command}")
    private String routingKeyCommand;
    @Value("${mq.routingkey.settingsInfo}")
    private String routingKeySettingsInfo;
    @Value("${mq.routingkey.my}")
    private String routingKeyMy;

    @GetMapping("send")
    public String send(String msg){
        rabbitTemplate.convertAndSend(directExchange, routingKeyCommand, msg);
        return "success";
    }

    @GetMapping("concurrentSend")
    public String concurrentSend(String msg){
        CyclicBarrier barrier = new CyclicBarrier(10);
        for(int i = 0; i < 10; i++){
            int finalI = i;
            new Thread(() -> {
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                rabbitTemplate.convertAndSend(directExchange, routingKeyMy, msg + finalI);
            }).start();
        }
        return "success";
    }

}
