package com.huntercodexs.demo.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Help4DevsAwsSdkBomSqsService {

    @Value("${queue.name}")
    String queueName;

    @Autowired
    AmazonSQS amazonSQS;

    public void createQueue() {
        amazonSQS.createQueue(queueName);
    }

    public void deleteQueue() {
        amazonSQS.deleteQueue(queueName);
    }

    public void messageProducer(String message) {
        String urlQueue = amazonSQS.getQueueUrl(queueName).getQueueUrl();
        amazonSQS.sendMessage(urlQueue, message);
        System.out.println("Message Produced: " + message);
    }

    public void messageConsumer(String filter) {

        String urlQueue = amazonSQS.getQueueUrl(queueName).getQueueUrl();
        ReceiveMessageResult receiveMessageRequest = amazonSQS.receiveMessage(urlQueue);

        for (Message message : receiveMessageRequest.getMessages()) {
            if (filter.isEmpty() || filter.equals(message.getMessageId()) || filter.equals(message.getBody())) {
                System.out.println("Message Consumed");
                System.out.println(message.getMessageId());
                System.out.println(message.getBody());
            }
        }
    }

    public void messageEraser(String filter) {

        String urlQueue = amazonSQS.getQueueUrl(queueName).getQueueUrl();
        ReceiveMessageResult receiveMessageRequest = amazonSQS.receiveMessage(urlQueue);

        for (Message message : receiveMessageRequest.getMessages()) {
            if (filter.isEmpty() || filter.equals(message.getMessageId()) || filter.equals(message.getBody())) {
                amazonSQS.deleteMessage(urlQueue, message.getReceiptHandle());
                System.out.println("Message Erased");
                System.out.println(message.getMessageId());
            }
        }
    }
}
