package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ExecutionException;

public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private String topicName;

    public void sendMessage(String msg) {
        kafkaTemplate.send(topicName, msg);
    }

    public void sendMessageWithCallback(String msg) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, msg);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Sent message=[" + msg +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + msg + "] due to : " + ex.getMessage());
            }
        });

    }

}
