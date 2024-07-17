package com.huntercodexs.demo.service;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Help4DevsAwsSqsService {

    @Value("${spring.cloud.aws.queue.name}")
    String queueName;

    @Autowired
    SqsTemplate sqsTemplate;

    public void messagePublisher(String message) {
        //sqsTemplate.send(MessageBuilder.withPayload(message).build());
        sqsTemplate.send(to -> to.queue(queueName).payload(message));
    }

}
