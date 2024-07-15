package com.huntercodexs.demo.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class Help4DevsAwsSdkSqsService {

    @Value("${queue.name}")
    String queueName;

    @Autowired
    AmazonSQS amazonSQS;

    public void createQueue() {

        CreateQueueRequest createQueueRequest = new CreateQueueRequest(queueName);
        String queueCreate = amazonSQS.createQueue(createQueueRequest).getQueueUrl();

        System.out.println("QUEUE URL");
        System.out.println(queueCreate);

    }

    public void listQueue() {

        ListQueuesResult queueList = amazonSQS.listQueues();

        System.out.println("QUEUE LIST");
        for (String queueUrl : queueList.getQueueUrls()) {
            System.out.println(queueUrl);
        }
    }

    public void deleteQueue() {

        DeleteQueueResult result = amazonSQS.deleteQueue(queueName);

        System.out.println("QUEUE DELETED");
        System.out.println(result);
    }

    public void sendMessage(String queueUrl, Object message) {

        Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();
        messageAttributes.put("Name", new MessageAttributeValue()
                .withStringValue("John")
                .withDataType("String"));

        SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(String.valueOf(message))
                .withMessageAttributes(messageAttributes)
                .withDelaySeconds(5);

        SendMessageResult result = amazonSQS.sendMessage(sendMessageRequest);

        System.out.println(result.getMessageId());

    }

    public void readMessage(String queueUrl, String target) {

        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest()
                .withQueueUrl(queueUrl)
                .withWaitTimeSeconds(20)
                .withVisibilityTimeout(20)
                .withMaxNumberOfMessages(10);

        List<Message> messageList = amazonSQS.receiveMessage(receiveMessageRequest).getMessages();

        for (Message item : messageList) {
            if (target.equals(item.getBody()) || target.equals(item.getMessageId()) || target.isEmpty()) {
                System.out.println("Message: " + item);
            }
        }

    }

    public void cancelMessage(String queueUrl, String target) {

        List<Message> messageList = amazonSQS.receiveMessage(queueUrl).getMessages();

        for (Message item : messageList) {
            if (target.equals(item.getBody()) || target.equals(item.getMessageId()) || target.isEmpty()) {
                amazonSQS.deleteMessage(queueUrl, item.getReceiptHandle());
            }
        }

    }

}
