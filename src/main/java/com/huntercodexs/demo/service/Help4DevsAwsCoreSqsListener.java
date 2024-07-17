package com.huntercodexs.demo.service;

import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Help4DevsAwsCoreSqsListener {

    @SqsListener("${spring.cloud.aws.queue.name}")
    public void messageConsumer(String message)  {
        System.out.println("Message Received: " + message);
    }

}
