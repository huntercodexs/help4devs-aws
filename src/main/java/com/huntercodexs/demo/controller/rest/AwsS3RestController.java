package com.huntercodexs.demo.controller.rest;

import com.huntercodexs.demo.dto.AwsS3RequestDto;
import com.huntercodexs.demo.dto.AwsS3ResponseDto;
import com.huntercodexs.demo.services.Help4DevsAwsS3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class AwsS3RestController {

	@Autowired
	Help4DevsAwsS3Service help4DevsAwsS3Service;

	@PostMapping(path = "${api.endpoint-add:/service/api/s3/add}")
	@ResponseBody
	public ResponseEntity<String> add(@RequestBody AwsS3RequestDto awsS3RequestDto) {
		log.info("Request received to add image");
		return new ResponseEntity<>(help4DevsAwsS3Service.saveToS3(awsS3RequestDto), HttpStatus.ACCEPTED);
	}

	@GetMapping(path = "${api.endpoint-read:/service/api/s3/read}/{guid}")
	@ResponseBody
	public ResponseEntity<AwsS3ResponseDto> read(@PathVariable String guid) {
		log.info("Request received to read image: {}", guid);
		AwsS3ResponseDto awsS3ResponseDto = new AwsS3ResponseDto();
		awsS3ResponseDto.setFile(help4DevsAwsS3Service.readFromS3(guid));
		return new ResponseEntity<>(awsS3ResponseDto, HttpStatus.OK);
	}

}
