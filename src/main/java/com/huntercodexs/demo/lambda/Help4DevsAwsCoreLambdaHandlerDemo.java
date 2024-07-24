package com.huntercodexs.demo.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class Help4DevsAwsCoreLambdaHandlerDemo implements RequestHandler<String, Object> {

    @Override
    public Object handleRequest(String input, Context context) {
        if (context == null) return input.toLowerCase();
        LambdaLogger logger = context.getLogger();
        logger.log("Function ["+context.getFunctionName()+"] called");
        return input.toUpperCase();
    }

}
