package com.huntercodexs.demo.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
import org.springframework.cloud.aws.messaging.core.QueueMessageChannel;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class Help4DevsAwsSdkSqsConfig {

    @Value("${cloud.aws.account-id}")
    String accountId;

    @Value("${cloud.aws.queue.name}")
    String queueName;

    @Value("${cloud.aws.region.static}")
    String region;

    @Value("${cloud.aws.credentials.accessKey}")
    String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    String secretKey;

    @Primary
    @Bean
    public AmazonSQSAsync amazonSQSAsync() {

        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        return AmazonSQSAsyncClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
    }

    @Bean
    public QueueMessagingTemplate queueMessagingTemplate() {

        String destination = "https://sqs."+region+".amazonaws.com/"+accountId+"/"+queueName;

        QueueMessagingTemplate template = new QueueMessagingTemplate(amazonSQSAsync());
        template.setDefaultDestination(new QueueMessageChannel(amazonSQSAsync(), destination));
        template.setDefaultDestinationName(destination);
        return template;
    }

    @Bean
    public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory() {
        SimpleMessageListenerContainerFactory factory = new SimpleMessageListenerContainerFactory();
        factory.setAmazonSqs(amazonSQSAsync());
        factory.setAutoStartup(true);
        return factory;
    }
}
