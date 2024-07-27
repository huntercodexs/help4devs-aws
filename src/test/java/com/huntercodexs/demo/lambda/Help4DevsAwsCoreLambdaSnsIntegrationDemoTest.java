package com.huntercodexs.demo.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.huntercodexs.demo.lambda.function.Help4DevsAwsCoreLambdaSnsIntegrationDemo;
import com.huntercodexs.demo.lambda.model.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class Help4DevsAwsCoreLambdaSnsIntegrationDemoTest {

    Help4DevsAwsCoreLambdaSnsIntegrationDemo handler;

    @Mock
    Context context;

    @Mock
    LambdaLogger lambdaLogger;

    @BeforeEach
    public void setup() {

        when(context.getLogger()).thenReturn(lambdaLogger);

        doAnswer(call -> {
            System.out.println((String) call.getArgument(0));
            return null;
        }).when(lambdaLogger).log(anyString());

        handler = new Help4DevsAwsCoreLambdaSnsIntegrationDemo();
    }

    @Test
    public void fakerTest() {
        System.out.println("Application is ok");
    }

    @Test
    public void handleRequestContextNullTest() {
        handler = new Help4DevsAwsCoreLambdaSnsIntegrationDemo();
        Response result = handler.handleRequest(new SNSEvent(), null);

        Assertions.assertEquals(result.getHttpCode(), 400);
        Assertions.assertEquals(result.getMessage(), "Context is null");
    }

    @Test
    public void handleRequestContextNotNullTest() {
        handler = new Help4DevsAwsCoreLambdaSnsIntegrationDemo();

        List<SNSEvent.SNSRecord> snsRecords = new ArrayList<>();
        SNSEvent snsEvent = new SNSEvent();
        snsEvent.setRecords(snsRecords);

        Response result = handler.handleRequest(snsEvent, context);

        Assertions.assertEquals(result.getHttpCode(), 200);
        Assertions.assertEquals(result.getMessage(), "OK");
    }

    @Test
    public void handleRequestContextNotNullAndSNSEventNullTest() {
        handler = new Help4DevsAwsCoreLambdaSnsIntegrationDemo();
        Response result = handler.handleRequest(new SNSEvent(), context);

        Assertions.assertEquals(result.getHttpCode(), 500);
        Assertions.assertEquals(result.getMessage(), "Internal Error");
    }

}