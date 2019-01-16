package com.rabbit.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by qinxy on 2018/11/21.
 */
@Component
@RabbitListener(queues = "demo")
public class Consumer2 {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @RabbitHandler
    public void consumer(String content){
        logger.info("consumer2:{}",content);
    }
}
