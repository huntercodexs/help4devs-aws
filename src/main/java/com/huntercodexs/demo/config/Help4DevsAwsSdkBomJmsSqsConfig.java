package com.huntercodexs.demo.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Help4DevsAwsSdkBomJmsSqsConfig {

    @Value("${cloud.aws.region.static}")
    String region;

    @Value("${cloud.aws.endpoint.uri:}")
    String endpointUri;

    @Bean
    public AmazonSQS amazonSQS() {

        if (endpointUri == null || endpointUri.isEmpty()) {
            endpointUri = "https://sqs."+region+".amazonaws.com/";
        }

        AwsClientBuilder.EndpointConfiguration endpointConfig = new AwsClientBuilder.EndpointConfiguration(
                endpointUri,
                region
        );

        AWSCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();

        return AmazonSQSClientBuilder.standard()
                .withCredentials(credentialsProvider)
                .withEndpointConfiguration(endpointConfig)
                .build();
    }
}
