package com.liledg.rabbitmq.sender.service;

import com.liledg.rabbitmq.sender.domain.Order;
import com.liledg.rabbitmq.sender.domain.OrderType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class MessageSenderService {

    private final Random random = new Random();

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.queue.name:orders}")
    private String queueName;

    @Scheduled(fixedRate = 100)
    public void sendMessage() {
        int id = random.nextInt(100000);
        Order order = new Order(id, "ORDER_" + id, OrderType.values()[id % 2]);
        
        rabbitTemplate.convertAndSend(queueName, order);
        log.info("Sent message: {}", order);
    }
}