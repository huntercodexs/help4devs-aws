package com.huntercodexs.demo.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification.S3EventNotificationRecord;
import com.amazonaws.services.s3.event.S3EventNotification;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huntercodexs.demo.lambda.function.Help4DevsAwsLambdaS3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.io.IOException;
import java.util.List;

import static com.huntercodexs.demo.lambda.Help4DevsAwsLambdaS3Event.eventRecords;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class Help4DevsAwsLambdaS3Test {

    Help4DevsAwsLambdaS3 handler;

    @Mock
    Context context;

    @Mock
    S3Event s3Event;

    @Mock
    LambdaLogger lambdaLogger;

    @BeforeEach
    public void setup() {

        when(context.getLogger()).thenReturn(lambdaLogger);

        doAnswer(call -> {
            System.out.println((String) call.getArgument(0));
            return null;
        }).when(lambdaLogger).log(anyString());

        handler = new Help4DevsAwsLambdaS3();
    }

    @Test
    public void fakerTest() {
        System.out.println("Application is ok");
    }

    @Test
    public void handleRequestContextNullTest() {
        handler = new Help4DevsAwsLambdaS3();
        Boolean result = handler.handleRequest(s3Event, null);
        Assertions.assertFalse(result);
    }

    @Test
    public void handleRequestContextNotNullAndS3EventNullTest() {
        handler = new Help4DevsAwsLambdaS3();
        Boolean result = handler.handleRequest(null, context);
        Assertions.assertFalse(result);
    }

    @Test
    public void handleRequestS3RecordsIsEmptyTest() {
        handler = new Help4DevsAwsLambdaS3();
        Boolean result = handler.handleRequest(new S3Event(), context);
        Assertions.assertFalse(result);
    }

    @Test
    public void handleRequestContextNotNullAndS3EventNotNullTest() throws IOException {
        handler = new Help4DevsAwsLambdaS3();

        ObjectMapper mapper = new ObjectMapper();

        S3EventNotification s3EventNotification = mapper.readValue(
                Help4DevsAwsLambdaS3Test.class.getResource("/s3-event-test.json"),
                S3EventNotification.class);

        List<S3EventNotificationRecord> recordList = eventRecords(s3EventNotification);
        s3Event = new S3Event(recordList);
        Boolean result = handler.handleRequest(s3Event, context);
        Assertions.assertTrue(result);
    }

}
