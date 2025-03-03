package com.huntercodexs.demo.services;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.stereotype.Service;

/**
 * Role Required
 * {
 *   "Version": "2012-10-17",
 *   "Statement": [
 *     {
 *       "Effect": "Allow",
 *       "Action": "sqs:SendMessage",
 *       "Resource": "arn:aws:sqs:us-east-1:{ACCOUNT-NUMBER}:my-sqs-queue-test.fifo"
 *     }
 *   ]
 * }
 * */

@Service
public class Help4DevsAwsSdkSqsService {

    private final AmazonSQSAsync amazonSQSAsync;
    private final String queueUrl = "https://sqs.us-east-1.amazonaws.com/{ACCOUNT-NUMBER}/my-sqs-queue-test.fifo";

    public Help4DevsAwsSdkSqsService(AmazonSQSAsync amazonSQSAsync) {
        this.amazonSQSAsync = amazonSQSAsync;
    }

    public void sendMessage(String message) {
        SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(message);
        amazonSQSAsync.sendMessage(sendMessageRequest);
    }

}
