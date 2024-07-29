package com.huntercodexs.demo.lambda;

import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification.*;
import com.amazonaws.services.s3.event.S3EventNotification;

import java.util.ArrayList;
import java.util.List;

public class Help4DevsAwsLambdaS3Event {

    public static List<S3EventNotificationRecord> eventRecords(
            S3EventNotification s3EventNotification
    ) {
        List<S3EventNotificationRecord> recordList = new ArrayList<>();
        S3EventNotification.S3EventNotificationRecord s3Records = s3EventNotification.getRecords().get(0);

        String region = s3Records.getAwsRegion();
        String eventName = s3Records.getEventName();
        String eventSource = s3Records.getEventSource();
        String eventTime = String.valueOf(s3Records.getEventTime());
        String eventVersion = s3Records.getEventVersion();
        String requestParameters = String.valueOf(s3Records.getRequestParameters());
        String xAmzId2 = String.valueOf(s3Records.getResponseElements().getxAmzId2());
        String xAmzRequestId = String.valueOf(s3Records.getResponseElements().getxAmzRequestId());
        String configurationId = String.valueOf(s3Records.getS3().getConfigurationId());
        String bucketName = s3Records.getS3().getBucket().getName();
        String principalId = String.valueOf(s3Records.getS3().getBucket().getOwnerIdentity().getPrincipalId());
        String arn = s3Records.getS3().getBucket().getArn();
        String objectKey = String.valueOf(s3Records.getS3().getObject().getKey());
        Long objectSize = s3Records.getS3().getObject().getSizeAsLong();
        String objectETag = s3Records.getS3().getObject().geteTag();
        String objectVersionId = s3Records.getS3().getObject().getVersionId();
        String objectSequencer = s3Records.getS3().getObject().getSequencer();

        S3EventNotificationRecord records = new S3EventNotificationRecord(
                region,
                eventName,
                eventSource,
                eventTime,
                eventVersion,
                new RequestParametersEntity(requestParameters),
                new ResponseElementsEntity(xAmzId2, xAmzRequestId),
                new S3Entity(
                        configurationId,
                        new S3BucketEntity(bucketName, new UserIdentityEntity(principalId), arn),
                        new S3ObjectEntity(objectKey, objectSize, objectETag, objectVersionId, objectSequencer),
                        eventVersion),
                new UserIdentityEntity(principalId)
        );

        recordList.add(records);
        return recordList;
    }
}