package com.rabbitmq.stockservice.consumer;

import com.rabbitmq.stockservice.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class EmailConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.queue.stock.name}"})
    public void consume(OrderEvent message){
        LOGGER.info(String.format("Order Event Received in Stock Service -> %s", message.toString()));
    }
}
