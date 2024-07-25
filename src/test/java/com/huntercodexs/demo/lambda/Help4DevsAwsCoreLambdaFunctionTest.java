package com.huntercodexs.demo.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.HashMap;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class Help4DevsAwsCoreLambdaFunctionTest {

    Help4DevsAwsCoreLambdaFunction handler;

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

        handler = new Help4DevsAwsCoreLambdaFunction();
    }

    @Test
    public void handleRequestTest() {
        handler = new Help4DevsAwsCoreLambdaFunction();
        APIGatewayProxyRequestEvent request = new APIGatewayProxyRequestEvent();
        request.setHttpMethod("POST");
        request.setHeaders(new HashMap<>());
        request.setBody("{\"test\": true}");
        request.setPath("/api/test");
        APIGatewayProxyResponseEvent result = handler.handleRequest(request, null);
        Assertions.assertEquals("{\"status\": false}",  result.getBody());
    }

    @Test
    public void handleRequestLoginValidateTest() {
        handler = new Help4DevsAwsCoreLambdaFunction();
        APIGatewayProxyRequestEvent request = new APIGatewayProxyRequestEvent();
        request.setHttpMethod("POST");
        request.setHeaders(new HashMap<>());
        request.setBody("{\"username\": \"admin\", \"password\": \"admin\"}");
        request.setPath("/api/test");
        APIGatewayProxyResponseEvent result = handler.handleRequest(request, context);
        Assertions.assertEquals("{\"isAuthorized\":true}",  result.getBody());
    }
}