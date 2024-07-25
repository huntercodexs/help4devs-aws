package com.huntercodexs.demo.lambda;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

class Help4DevsAwsCoreLambdaFunctionTest {

    Help4DevsAwsCoreLambdaFunction handler;

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
}