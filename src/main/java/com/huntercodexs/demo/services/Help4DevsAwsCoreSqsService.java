package com.huntercodexs.demo.services;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Help4DevsAwsCoreSqsService {

    @Autowired
    SqsTemplate sqsTemplate;

    public void messagePublisher(String endpointName, String message) {
        sqsTemplate.send(endpointName, message);
        System.out.println("Message Publisher");
        System.out.println("Url: " + endpointName);
        System.out.println("Message: " + message);
    }

}
