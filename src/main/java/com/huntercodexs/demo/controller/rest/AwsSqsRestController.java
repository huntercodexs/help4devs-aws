package com.huntercodexs.demo.controller.rest;

import com.huntercodexs.demo.services.Help4DevsAwsSqsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AwsSqsRestController {

	@Autowired
	Help4DevsAwsSqsService help4DevsAwsSqsService;


}
