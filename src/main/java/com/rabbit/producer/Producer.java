package com.rabbit.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by qinxy on 2018/11/21.
 */
@Component
public class Producer {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String msg){
        String content = msg + (new Date()).toString();
        logger.info("send:{}",content);
        Message message = MessageBuilder.withBody(content.getBytes()).build();
        amqpTemplate.convertAndSend("hello",message);
    }

    public void demo(String msg){
        String content = msg + (new Date()).toString();
        logger.info("demo:{}",content);
        amqpTemplate.convertAndSend("demo",content);
    }
}
