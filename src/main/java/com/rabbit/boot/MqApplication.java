package com.rabbit.boot;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.rabbit")
@Configurable
@EnableRabbit
public class MqApplication {

	public static void main(String[] args) {
		SpringApplication.run(MqApplication.class, args);
	}
}
