package com.huntercodexs.demo.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import com.amazonaws.services.securitytoken.model.AssumeRoleRequest;
import com.amazonaws.services.securitytoken.model.AssumeRoleResult;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Help4DevsAwsSdkSqsConfig {

    @Bean
    public AmazonSQSAsync amazonSQSAsync() {
        AWSSecurityTokenService stsClient = AWSSecurityTokenServiceClientBuilder.standard().build();
        AssumeRoleRequest assumeRoleRequest = new AssumeRoleRequest()
                .withRoleArn("arn:aws:iam::{ACCOUNT-NUMBER}:role/SQSMessageSenderRole")
                .withRoleSessionName("sessionName");
        AssumeRoleResult assumeRoleResult = stsClient.assumeRole(assumeRoleRequest);

        BasicSessionCredentials sessionCredentials = new BasicSessionCredentials(
                assumeRoleResult.getCredentials().getAccessKeyId(),
                assumeRoleResult.getCredentials().getSecretAccessKey(),
                assumeRoleResult.getCredentials().getSessionToken()
        );

        return AmazonSQSAsyncClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(sessionCredentials))
                .withRegion(Regions.US_EAST_1)
                .build();
    }
}
