package com.ghostrick.SpringAMQPexample;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringAmqpExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAmqpExampleApplication.class, args);
	}

	@Bean
	public ApplicationRunner runner(RabbitTemplate template) {
		return args -> {
			template.convertAndSend("myQueue", "Hello, world!");
		};
	}

	@Bean
	public Queue myQueue() {
		return new Queue("myQueue", false);
	}

	@RabbitListener(queues = "myQueue")
	public void listen(String in) {
		System.out.println("Message read from myQueue : " + in);
	}
}
