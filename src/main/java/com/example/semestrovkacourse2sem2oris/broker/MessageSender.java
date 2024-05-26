//package com.example.semestrovkacourse2sem2oris.broker;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class MessageSender {
//
//    private final RabbitTemplate rabbitTemplate;
//    private final Queue myQueue;
//
//    public void sendMessage(RabbitMQDto message) {
//        rabbitTemplate.convertAndSend(myQueue.getName(), message.toString().getBytes());
//    }
//}