package com.huntercodexs.demo.lambda.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class Help4DevsAwsLambdaFunctionConfig {

    @Bean
    public Function<String, String> reverse() {
        return (input) -> String.valueOf(new StringBuilder(input).reverse());
    }
}
