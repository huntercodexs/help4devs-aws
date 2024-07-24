package com.huntercodexs.demo.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class Help4DevsAwsCoreLambdaHandlerDemoTest {

    Help4DevsAwsCoreLambdaHandlerDemo handler;

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

        handler = new Help4DevsAwsCoreLambdaHandlerDemo();
    }

    @Test
    public void handleRequestTest() {
        handler = new Help4DevsAwsCoreLambdaHandlerDemo();
        Assertions.assertEquals("java17 for all", handler.handleRequest("Java17 For All", null));
    }


    @Test
    public void handleRequestContextNotNullTest() {
        when(context.getFunctionName()).thenReturn("handleRequest");
        Assertions.assertEquals("HELLO WORLD JAVA17", handler.handleRequest("Hello World java17", context));
    }

}