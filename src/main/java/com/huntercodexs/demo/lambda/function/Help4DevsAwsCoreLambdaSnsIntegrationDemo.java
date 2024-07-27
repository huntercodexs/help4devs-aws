package com.huntercodexs.demo.lambda.function;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.huntercodexs.demo.lambda.model.Product;
import com.huntercodexs.demo.lambda.model.Response;
import lombok.extern.log4j.Log4j2;

import java.net.HttpURLConnection;
import java.util.Optional;

@Log4j2
public class Help4DevsAwsCoreLambdaSnsIntegrationDemo implements
        RequestHandler<SNSEvent, Response>
{
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Response handleRequest(SNSEvent event, Context context) {

        if (context == null) {
            log.error("Context is null");
            return Response.builder()
                    .message("Context is null")
                    .httpCode(HttpURLConnection.HTTP_BAD_REQUEST)
                    .build();
        }

        log.info("Handle Request Event: {}", event);
        log.info("Handle Request Context: {}", context);

        try {

            Optional<SNSEvent.SNSRecord> snsRecord = event.getRecords().stream().findAny();

            if (snsRecord.isPresent()) {
                SNSEvent.SNS sns = snsRecord.get().getSNS();
                String jsonMessage = sns.getMessage();
                Product product = gson.fromJson(jsonMessage, Product.class);
                log.info("Product Object: {}", product.toString());
            }

            log.info("Handle Request SNSRecord: {}", snsRecord);

            return Response.builder()
                    .httpCode(HttpURLConnection.HTTP_OK)
                    .message("OK")
                    .build();

        } catch (Exception e) {

            log.error("Internal Server Error: {}", e.getMessage());
            return Response.builder()
                    .httpCode(HttpURLConnection.HTTP_INTERNAL_ERROR)
                    .message("Internal Error")
                    .build();
        }
    }
}
