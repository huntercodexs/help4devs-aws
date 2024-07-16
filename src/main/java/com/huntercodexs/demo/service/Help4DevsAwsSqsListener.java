package com.huntercodexs.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Help4DevsAwsSqsListener {

    @SqsListener("${cloud.aws.queue.name}")
    public void messageConsumer(String message)  {
        System.out.println("Message Received: " + message);
    }

}
