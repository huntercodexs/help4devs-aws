package com.huntercodexs.demo.services;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.stereotype.Service;

@Service
public class Help4DevsAwsSdkSqsService {

    private final AmazonSQSAsync amazonSQSAsync;
    private final String queueUrl = "https://sqs.us-east-1.amazonaws.com/{ACCOUNT-NUMBER}/{MY-QUEUE-NAME}";

    public Help4DevsAwsSdkSqsService(AmazonSQSAsync amazonSQSAsync) {
        this.amazonSQSAsync = amazonSQSAsync;
    }

    public void sendMessage(String message) {
        SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(message)
                .withMessageGroupId("default");
        amazonSQSAsync.sendMessage(sendMessageRequest);
    }

}
