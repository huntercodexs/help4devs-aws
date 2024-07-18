package com.huntercodexs.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Help4DevsAwsSdkSqsService {

    @Value("${cloud.aws.queue.name}")
    String staticQueueName;

    @Autowired
    QueueMessagingTemplate queueMessagingTemplate;

    public void messagePublisherBuilder(String message) {
        queueMessagingTemplate.send(MessageBuilder.withPayload(message).build());
        System.out.println("Message Publisher Builder: " + message);
    }

    public void messagePublisherStaticQueue(String message) {
        queueMessagingTemplate.convertAndSend(staticQueueName, message);
        System.out.println("Message publisher to queue: " + message);
    }

    public void messagePublisherConvert(String message, String sqsQueueName) {
        queueMessagingTemplate.convertAndSend(sqsQueueName, message);
        System.out.println("Message publisher to queue: " + message);
    }

}
