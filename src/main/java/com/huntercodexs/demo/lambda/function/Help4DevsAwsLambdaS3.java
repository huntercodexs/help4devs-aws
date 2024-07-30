package com.huntercodexs.demo.lambda.function;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.huntercodexs.demo.lambda.client.Help4DevsAwsS3Client;
import com.huntercodexs.demo.lambda.processor.Help4DevsAwsS3FileProcessor;

import static com.huntercodexs.demo.lambda.processor.Help4DevsAwsS3FileProcessor.fileExtension;

public class Help4DevsAwsLambdaS3 implements RequestHandler<S3Event, Boolean> {

    Help4DevsAwsS3Client client = new Help4DevsAwsS3Client();

    AmazonS3 amazonS3 = client.amazonS3Default();

    @Override
    public Boolean handleRequest(S3Event eventInput, Context context) {

        Help4DevsAwsS3FileProcessor processor = new Help4DevsAwsS3FileProcessor();

        if (context == null) {
            System.out.println("Context is null");
            return false;
        }

        LambdaLogger logger = context.getLogger();

        if (eventInput == null) {
            logger.log("S3Event is null");
            return false;
        }

        if (eventInput.getRecords().isEmpty()) {
            logger.log("Missing event records");
            return false;
        }

        eventInput.getRecords().forEach(record -> {
            String bucketName = record.getS3().getBucket().getName();
            String objectKey = record.getS3().getObject().getKey();

            S3Object s3Object = amazonS3.getObject(bucketName, objectKey);
            S3ObjectInputStream inputStream = s3Object.getObjectContent();

            switch (fileExtension(objectKey)) {
                case "csv":
                    processor.csv(logger, inputStream);
                    break;

                case "pdf":
                    processor.pdf(logger, inputStream, "metadata");
                    break;

                case "png":
                    processor.png(logger, inputStream);
                    break;

                case "jpg":
                case "jpeg":
                    processor.jpg(logger, inputStream);
                    break;

                default:
                    processor.txt(logger, inputStream);

            }
        });

        return true;

    }

}
