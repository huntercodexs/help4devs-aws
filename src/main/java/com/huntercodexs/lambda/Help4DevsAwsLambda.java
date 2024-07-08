package com.huntercodexs.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.springframework.http.HttpStatus;

public class Help4DevsAwsLambda implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    LambdaLogger lambdaLogger;

    @Override
    public APIGatewayProxyResponseEvent handleRequest(
            APIGatewayProxyRequestEvent request,
            Context context
    ) {
        lambdaLogger = context.getLogger();

        lambdaLogger.log("Request lambda received: " + request.getBody());

        String response = "{\"result\": true}";

        lambdaLogger.log("Response: " + response);

        return new APIGatewayProxyResponseEvent()
                .withStatusCode(HttpStatus.OK.value())
                .withBody(response)
                .withIsBase64Encoded(false);

    }

}
