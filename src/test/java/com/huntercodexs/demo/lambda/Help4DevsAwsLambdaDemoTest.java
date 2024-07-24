package com.huntercodexs.demo.lambda;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Help4DevsAwsLambdaDemoTest {

    @Test
    void handleRequestTest() {
        Help4DevsAwsLambdaDemo help4DevsAwsLambdaDemo = new Help4DevsAwsLambdaDemo();
        assertEquals("Hello, Im an AWS LAMBDA Demo", help4DevsAwsLambdaDemo.handleRequest());
    }

    @Test
    void handlerRequestNameTest() {
        Help4DevsAwsLambdaDemo help4DevsAwsLambdaDemo = new Help4DevsAwsLambdaDemo();
        assertEquals("Hello, Jereelton", help4DevsAwsLambdaDemo.handleRequestName("Jereelton"));
    }
}