package com.huntercodexs.demo.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

public class Help4DevsAwsCoreLambdaFunction implements
        RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent>
{

    @Override
    public APIGatewayProxyResponseEvent handleRequest(
            APIGatewayProxyRequestEvent request, Context context
    ) {
        if (context == null) {
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(400)
                    .withBody("{\"status\": false}")
                    .withIsBase64Encoded(false);
        }

        var logger = context.getLogger();

        logger.log("Request received - " + request.getBody());

        return new APIGatewayProxyResponseEvent()
                .withStatusCode(200)
                .withBody("{\"status\": true}")
                .withIsBase64Encoded(false);
    }

}
