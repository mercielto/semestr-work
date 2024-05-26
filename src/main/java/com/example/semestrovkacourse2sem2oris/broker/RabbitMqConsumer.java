//package com.example.semestrovkacourse2sem2oris.broker;
//
//import org.springframework.amqp.rabbit.annotation.EnableRabbit;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@Component
//@EnableRabbit
//public class RabbitMqConsumer {
//
//    @RabbitListener(queues = "image")
//    public void processMyQueue(Object message) {
//        if (message instanceof RabbitMQDto) {
//            System.out.println("dto");
//        }
//        System.out.printf("Received from myQueue : %s \n", message);
//    }
//}
