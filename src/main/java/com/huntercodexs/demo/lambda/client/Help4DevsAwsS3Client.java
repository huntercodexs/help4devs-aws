package com.huntercodexs.demo.lambda.client;

import com.amazonaws.auth.*;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class Help4DevsAwsS3Client {

    public AmazonS3 amazonS3Default() {

        return AmazonS3Client.builder()
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();

    }

    public AmazonS3 amazonS3Basic(String accessKey, String secretKey, String region) {

        if (region == null || region.isEmpty()) {
            region = "us-east-1";
        }

        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
    }

    public AmazonS3 amazonS3Provider(String region) {

        if (region == null || region.isEmpty()) {
            region = "us-east-1";
        }

        AWSCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();

        return AmazonS3ClientBuilder.standard()
                .withCredentials(credentialsProvider)
                .withRegion(region)
                .build();

    }

    public AmazonS3 amazonS3EndpointConfig(String endpointUri, String region) {

        if (region == null || region.isEmpty()) {
            region = "us-east-1";
        }

        if (endpointUri == null || endpointUri.isEmpty()) {
            endpointUri = "s3.amazonaws.com";
        }

        AwsClientBuilder.EndpointConfiguration endpointConfig = new AwsClientBuilder.EndpointConfiguration(
                endpointUri,
                region
        );

        AWSCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();

        return AmazonS3ClientBuilder.standard()
                .withCredentials(credentialsProvider)
                .withEndpointConfiguration(endpointConfig)
                .build();

    }

}
