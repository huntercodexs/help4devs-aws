package com.huntercodexs.demo.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessageChannel;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class Help4DevsAwsCoreSqsConfig {

    @Value("${cloud.aws.account-id}")
    String accountId;

    @Value("${cloud.aws.queue.name}")
    String queueName;

    @Value("${cloud.aws.region.static}")
    String region;

    @Bean
    public QueueMessagingTemplate queueMessagingTemplate() {

        String destination = "https://sqs."+region+".amazonaws.com/"+accountId+"/"+queueName;

        QueueMessagingTemplate template = new QueueMessagingTemplate(amazonSQSAsync());
        template.setDefaultDestination(new QueueMessageChannel(amazonSQSAsync(), destination));
        template.setDefaultDestinationName(destination);
        return template;
    }

    @Primary
    @Bean
    public AmazonSQSAsync amazonSQSAsync() {

        AWSCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();

        return AmazonSQSAsyncClientBuilder.standard()
                .withCredentials(credentialsProvider)
                .withRegion(region)
                .build();
    }
}
