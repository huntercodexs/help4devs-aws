package com.huntercodexs.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.net.URI;

@Configuration
public class Help4DevsAwsCoreSqsConfig {

    @Value("${cloud.aws.queue.name}")
    String queueName;

    @Value("${cloud.aws.region.static}")
    String region;

    @Value("${cloud.aws.endpoint.uri:}")
    String endpointUri;

    @Bean
    @Primary
    public SqsAsyncClient sqsAsyncClient() {

        if (endpointUri == null || endpointUri.isEmpty()) {
            endpointUri = "https://sqs."+region+".amazonaws.com/";
        }

        return SqsAsyncClient.builder()
                .endpointOverride(URI.create(endpointUri))
                .region(Region.of(region))
                .build();
    }
}
