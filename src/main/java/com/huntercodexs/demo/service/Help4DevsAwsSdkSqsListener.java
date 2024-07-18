package com.huntercodexs.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableSqs
public class Help4DevsAwsSdkSqsListener {

    @SqsListener(
            value = "${cloud.aws.queue.name}",
            deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void messageConsumer(String message) {
        System.out.println("Message Consumer: " + message);
    }

}
