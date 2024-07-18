package com.huntercodexs.demo.config;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@Configuration
public class Help4DevsAwsSqsConfig {

    @Value("${cloud.aws.region.static}")
    String region;

    @Value("${cloud.aws.credentials.accessKey}")
    String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    String secretKey;

    @Bean
    public SqsTemplate sqsTemplate() {
        return SqsTemplate.builder().sqsAsyncClient(sqsAsyncClient()).build();
    }

    @Bean
    @Primary
    public SqsAsyncClient sqsAsyncClient() {

        return SqsAsyncClient.builder()
                .credentialsProvider(StaticCredentialsProvider
                        .create(AwsBasicCredentials.create(accessKey, secretKey)))
                .region(Region.of(region))
                .build();

    }
}
