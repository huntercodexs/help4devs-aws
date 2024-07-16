package com.huntercodexs.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Help4DevsAwsCoreSqsService {

    @Autowired
    QueueMessagingTemplate queueMessagingTemplate;

    public void messagePublisher(String message) {
        queueMessagingTemplate.send(MessageBuilder.withPayload(message).build());
    }

}
