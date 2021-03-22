package com.example.demo;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import javax.swing.*;

public class KafkaConsumer {

    @KafkaListener(topics = "topicName", groupId = "foo")
    public void listenGroupFoo(Spring message) {
        System.out.println("Received Message in group foo: " + message);
    }

    @KafkaListener(topics = "topicName")
    public void listenWithHeaders(
            @Payload String message,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        System.out.println(
                "Received Message: " + message
                        + "from partition: " + partition);
    }
}
