package com.huntercodexs.demo.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class Help4DevsAwsSdkSqsService {

    @Autowired
    AmazonSQS amazonSQS;

    public void queueCreator(String queueName) {
        CreateQueueRequest createQueueRequest = new CreateQueueRequest(queueName);
        String queueUrl = amazonSQS.createQueue(createQueueRequest).getQueueUrl();
        System.out.println("Queue created: " + queueUrl);
    }

    public void messageProducer(String message, String queueUrl) {
        SendMessageRequest request = new SendMessageRequest().withQueueUrl(queueUrl).withMessageBody(message);
        String messageId = amazonSQS.sendMessage(request).getMessageId();
        System.out.println("Message created: " + messageId);
    }

    public void messageConsumer(String filter, String queueUrl) {
        ReceiveMessageRequest request = new ReceiveMessageRequest(queueUrl)
                .withWaitTimeSeconds(10)
                .withMaxNumberOfMessages(10);

        List<Message> messageList = amazonSQS.receiveMessage(request).getMessages();

        for (Message message : messageList) {
            if (filter.isEmpty() || filter.equals(message.getMessageId()) || filter.equals(message.getBody())) {
                System.out.println("Message Received:");
                System.out.println(message.getMessageId());
                System.out.println(message.getBody());
                System.out.println(message.getReceiptHandle());
            }
        }
    }

    public void messageEraser(String receiptHandle, String queueUrl) {
        amazonSQS.deleteMessage(new DeleteMessageRequest()
                .withQueueUrl(queueUrl)
                .withReceiptHandle(receiptHandle));
    }

    public void queueEraser(String queueName) {
        amazonSQS.deleteQueue(queueName);
    }

}
