package com.ghostrick.SpringAMQPexample.broadcast;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BroadCastMessageApp {

    private static final String ROUTING_KEY_USER_IMPORTANT_WARN = "user.important.warn";
    private static final String ROUTING_KEY_USER_IMPORTANT_ERROR = "user.important.error";

    public static void main(String[] args){
        SpringApplication.run(BroadCastMessageApp.class, args);
    }

    @Bean
    public ApplicationRunner runner(RabbitTemplate rabbitTemplate) {
        String message = "Payload is broadcast";
        return args -> {
          rabbitTemplate.convertAndSend(BroadCastConfig.FANOUT_EXCHANGE_NAME,
                  "fanout",
                  message);
          rabbitTemplate.convertAndSend(BroadCastConfig.TOPIC_EXCHANGE_NAME,
                  ROUTING_KEY_USER_IMPORTANT_WARN,
                  "topic important warn" + message);
          rabbitTemplate.convertAndSend(BroadCastConfig.TOPIC_EXCHANGE_NAME,
                  ROUTING_KEY_USER_IMPORTANT_ERROR,
                  "topic important error" + message);
        };
    }

    @RabbitListener(queues = {BroadCastConfig.FANOUT_QUEUE_1_NAME})
    public void receiveMessagesFromFanout1(String message) {
        System.out.println("Received fanout 1 message: " + message);
    }

    @RabbitListener(queues = {BroadCastConfig.FANOUT_QUEUE_2_NAME})
    public void receiveMessagesFromFanout2(String message) {
        System.out.println("Received fanout 2 message: " + message);
    }

    @RabbitListener(queues = {BroadCastConfig.TOPIC_QUEUE_1_NAME})
    public void receiveMessagesFromTopic1(String message) {
        System.out.println("Received Topic 1 ( " + BroadCastConfig.BINDING_PATTERN_IMPORTANT + ") message: " + message);
    }
    @RabbitListener(queues = {BroadCastConfig.TOPIC_QUEUE_1_NAME})
    public void receiveMessagesFromTopic2(String message) {
        System.out.println("Received Topic 2 ( " + BroadCastConfig.BINDING_PATTERN_ERROR + ") message: " + message);
    }
}
