package com.huntercodexs.demo.services;

import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Help4DevsAwsCoreSqsListener {

    @SqsListener("${cloud.aws.queue.name}")
    public void messageConsumer(String message) {
        System.out.println("Message received: " + message);
    }

}
