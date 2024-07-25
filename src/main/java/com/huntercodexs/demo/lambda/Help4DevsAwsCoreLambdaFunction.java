package com.huntercodexs.demo.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huntercodexs.demo.lambda.request.LoginValidateRequest;
import com.huntercodexs.demo.lambda.response.LoginValidateResponse;

import java.io.IOException;
import java.io.UncheckedIOException;

public class Help4DevsAwsCoreLambdaFunction implements
        RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent>
{
    static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
    }

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

        try {

            logger.log("Request received - " + request.getBody());

            var loginRequest = objectMapper.readValue(request.getBody(), LoginValidateRequest.class);

            /*Login validate*/
            if (loginRequest.username().equals("admin") && loginRequest.password().equals("admin")) {
                return new APIGatewayProxyResponseEvent()
                        .withStatusCode(200)
                        .withBody(objectMapper.writeValueAsString(new LoginValidateResponse(true)))
                        .withIsBase64Encoded(false);
            }

            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(401)
                    .withBody(objectMapper.writeValueAsString(new LoginValidateResponse(false)))
                    .withIsBase64Encoded(false);


        } catch (IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }

}
