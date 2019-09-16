package org.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public final static String EXCHANGE_NAME = "topic-exchange";
    public final static String TEST_QUEUE_NAME = "test-queue";
    public final static String ROUTING_KEY = "default-topic-key";

    @Bean
    public Queue testQueue(){
        return new  Queue(TEST_QUEUE_NAME, true);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding bindingExchange(Queue queueMessage, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueMessage).to(topicExchange).with(ROUTING_KEY);
    }

}
