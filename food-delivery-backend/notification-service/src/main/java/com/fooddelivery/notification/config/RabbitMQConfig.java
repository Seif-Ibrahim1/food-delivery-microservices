package com.fooddelivery.notification.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "notification.queue";
    public static final String EXCHANGE_NAME = "order.exchange";
    public static final String ROUTING_KEY = "order.placed";

    // 1. Create the Queue
    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME);
    }

    // 2. Create the Topic Exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    // 3. Bind the Queue to the Exchange
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    // 4. Message Converter (JSON) - Important for Clean Code!
    // This allows us to send Java Objects, and they automatically become JSON in the queue.
    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }
}