package com.huntercodexs.demo.service;

import com.amazon.sqs.javamessaging.AmazonSQSMessagingClientWrapper;
import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.QueueAttributeName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class Help4DevsAwsJmsSqsService {

    @Value("${queue.name}")
    String queueName;

    @Autowired
    AmazonSQS amazonSQS;

    private SQSConnection connect() throws JMSException {
        SQSConnectionFactory connectionFactory = new SQSConnectionFactory(new ProviderConfiguration(), amazonSQS);
        SQSConnection connection = connectionFactory.createConnection();
        System.out.println("Connection created");
        return connection;
    }

    private void createQueueStandard(SQSConnection connection) throws JMSException {
        AmazonSQSMessagingClientWrapper clientWrapper = connection.getWrappedAmazonSQSClient();

        if (!clientWrapper.queueExists(queueName)) {
            clientWrapper.createQueue(queueName);
            System.out.println("Queue created");
        }
    }

    private void createQueueFifo(SQSConnection connection) throws JMSException {

        Map<String, String> queueAttributes = new HashMap<>();
        queueAttributes.put(String.valueOf(QueueAttributeName.FifoQueue), "true");
        queueAttributes.put(String.valueOf(QueueAttributeName.ContentBasedDeduplication), "true");
        queueAttributes.put("DeduplicationScope", "messageGroup");
        queueAttributes.put("FifoThroughputLimit", "perMessageGroupId");

        CreateQueueRequest createQueueRequest = new CreateQueueRequest();
        createQueueRequest
                .withQueueName(queueName+".fifo")
                .withAttributes(queueAttributes);

        AmazonSQSMessagingClientWrapper clientWrapper = connection.getWrappedAmazonSQSClient();

        if (!clientWrapper.queueExists(queueName+".fifo")) {
            clientWrapper.createQueue(createQueueRequest);
            System.out.println("Queue created");
        }

    }

    private Session createSession(SQSConnection connection) throws JMSException {
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        System.out.println("Session created");
        return session;
    }

    private Queue createSessionQueueStandard(Session session) throws JMSException {
        Queue queue = session.createQueue(queueName);
        System.out.println("Session Queue created");
        return queue;
    }

    private Queue createSessionQueueFifo(Session session) throws JMSException {
        Queue queue = session.createQueue(queueName+".fifo");
        System.out.println("Session Queue created");
        return queue;
    }

    private void producerStandard(SQSConnection connection, Session session, Queue queue, String message)
            throws JMSException
    {
        MessageProducer producer = session.createProducer(queue);
        TextMessage textMessage = session.createTextMessage(message);
        connection.start();
        producer.send(textMessage);
        System.out.println("Producer Standard Message finished");
    }

    private void producerFifo(SQSConnection connection, Session session, Queue queue, String message)
            throws JMSException
    {
        MessageProducer producer = session.createProducer(queue);
        TextMessage textMessage = session.createTextMessage(message);
        textMessage.setStringProperty("JMSXGroupID", "sqs-help4devs-group-id-test");
        connection.start();
        producer.send(textMessage);
        System.out.println("Producer Fifo Message finished");
    }

    private void consumer(SQSConnection connection, Session session, Queue queue)
            throws JMSException, InterruptedException
    {
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(new Help4DevsAwsJmsSqsLister());
        connection.start();
        Thread.sleep(1000);
        System.out.println("Consumer Message finished");
    }

    public void messageProducerStandard(String message) throws JMSException {
        SQSConnection connection = connect();
        createQueueStandard(connection);
        Session session = createSession(connection);
        Queue queue = createSessionQueueStandard(session);
        producerStandard(connection, session, queue, message);
    }

    public void messageProducerFifo(String message) throws JMSException {
        SQSConnection connection = connect();
        createQueueFifo(connection);
        Session session = createSession(connection);
        Queue queue = createSessionQueueFifo(session);
        producerFifo(connection, session, queue, message);
    }

    public void messageConsumerStandard() throws JMSException, InterruptedException {
        SQSConnection connection = connect();
        createQueueStandard(connection);
        Session session = createSession(connection);
        Queue queue = createSessionQueueStandard(session);
        consumer(connection, session, queue);
    }

    public void messageConsumerFifo() throws JMSException, InterruptedException {
        SQSConnection connection = connect();
        createQueueFifo(connection);
        Session session = createSession(connection);
        Queue queue = createSessionQueueFifo(session);
        consumer(connection, session, queue);
    }

}
