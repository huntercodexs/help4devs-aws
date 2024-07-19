package com.huntercodexs.demo;

import com.huntercodexs.demo.services.Help4DevsAwsCoreSqsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Help4DevsAwsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Help4DevsAwsApplication.class, args);
	}

	@Autowired
	Help4DevsAwsCoreSqsService help4DevsAwsCoreSqsService;

	@Override
	public void run(String... args) {
		help4DevsAwsCoreSqsService.messagePublisher(
				"{ENDPOINT-NAME-URL}",
				"test sqs 00008");
	}

}
