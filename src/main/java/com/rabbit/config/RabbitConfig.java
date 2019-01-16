package com.rabbit.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by qinxy on 2018/11/21.
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue helloQueue(){
        return new Queue("hello");
    }

    @Bean
    public Queue DemoQueue(){
        return new Queue("demo");
    }

}
