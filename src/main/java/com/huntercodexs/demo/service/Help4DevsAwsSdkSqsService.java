package com.huntercodexs.demo.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.DeleteQueueResult;
import com.amazonaws.services.sqs.model.ListQueuesResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Help4DevsAwsSdkSqsService {

    @Value("${queue.name}")
    String queueName;

    @Autowired
    AmazonSQS amazonSQS;

    public void create() {

        CreateQueueRequest createQueueRequest = new CreateQueueRequest(queueName);
        String queueCreate = amazonSQS.createQueue(createQueueRequest).getQueueUrl();

        System.out.println("QUEUE URL");
        System.out.println(queueCreate);

    }

    public void list() {

        ListQueuesResult queueList = amazonSQS.listQueues();

        System.out.println("QUEUE LIST");
        for (String queueUrl : queueList.getQueueUrls()) {
            System.out.println(queueUrl);
        }
    }

    public void delete() {

        DeleteQueueResult result = amazonSQS.deleteQueue(queueName);

        System.out.println("QUEUE DELETED");
        System.out.println(result);
    }
}
