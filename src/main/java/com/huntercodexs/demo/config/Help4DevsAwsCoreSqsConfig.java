package com.huntercodexs.demo.config;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.net.URI;

@Configuration
public class Help4DevsAwsCoreSqsConfig {

    @Value("${cloud.aws.region.static}")
    String region;

    @Value("${cloud.aws.endpoint.uri:}")
    String endpointUri;

    @Bean
    public SqsTemplate sqsTemplate() {
        return SqsTemplate.builder().sqsAsyncClient(sqsAsyncClient()).build();
    }

    @Bean
    @Primary
    public SqsAsyncClient sqsAsyncClient() {

        if (endpointUri == null || endpointUri.isEmpty()) {
            endpointUri = "https://sqs."+region+".amazonaws.com/";
        }

        AwsCredentialsProvider credentialsProvider = ProfileCredentialsProvider.create();

        return SqsAsyncClient.builder()
                .credentialsProvider(credentialsProvider)
                .endpointOverride(URI.create(endpointUri))
                .region(Region.of(region))
                .build();
    }
}
