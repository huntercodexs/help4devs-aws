package com.huntercodexs.demo.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Help4DevsAwsSdkSqsConfig {

    @Value("${cloud.aws.region.static}")
    String region;

    @Bean
    public AmazonSQS amazonSQS() {

        AWSCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();

        return AmazonSQSClientBuilder.standard()
                .withCredentials(credentialsProvider)
                .withRegion(region)
                .build();
    }
}
