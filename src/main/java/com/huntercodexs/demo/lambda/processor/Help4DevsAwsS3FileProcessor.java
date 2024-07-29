package com.huntercodexs.demo.lambda.processor;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Help4DevsAwsS3FileProcessor {

    public void csv(LambdaLogger logger, S3ObjectInputStream inputStream) {

        logger.log("File processor STARTED");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            br.lines().skip(1).forEach(line -> {
                logger.log("Processing File: " + line + "\n");
            });

        } catch (IOException e) {
            logger.log("ERROR: Something went wrong in the file processor: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        logger.log("File processor FINISHED");

    }

}
