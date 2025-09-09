package com.liledg.rabbitmq.consumer.service;

import com.liledg.rabbitmq.consumer.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageConsumerService {

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void receiveMessage(Order order) {
        log.info("Received message: {}", order);
    }
}