package com.fooddelivery.notification.listener;

import com.fooddelivery.notification.event.OrderPlacedEvent;
import lombok.extern.slf4j.Slf4j; // Lombok Logger
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j // Gives us the 'log' variable
public class NotificationListener {

    @RabbitListener(queues = "notification.queue")
    public void handleOrderPlaced(OrderPlacedEvent event) {
        // In the real world, we would send an email here.
        log.info("📧 RECEIVED NOTIFICATION: Sending email to {} for Order ID: {}", event.email(), event.orderId());
    }
}