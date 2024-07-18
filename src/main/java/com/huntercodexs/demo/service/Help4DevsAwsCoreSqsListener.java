package com.huntercodexs.demo.service;

import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Help4DevsAwsCoreSqsListener {

    @SqsListener(value = "${cloud.aws.queue.name}", acknowledgementMode = "true")
    public void messageConsumer(String message)  {
        System.out.println("Message Received: " + message);
    }

}
