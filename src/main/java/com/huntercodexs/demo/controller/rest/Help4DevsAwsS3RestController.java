package com.huntercodexs.demo.controller.rest;

import com.huntercodexs.demo.dto.Help4DevsAwsS3RequestDto;
import com.huntercodexs.demo.dto.Help4DevsAwsS3ResponseDto;
import com.huntercodexs.demo.services.Help4DevsAwsS3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class Help4DevsAwsS3RestController {

	@Autowired
	Help4DevsAwsS3Service help4DevsAwsS3Service;

	@PostMapping(path = "/api/s3/deprecated/upload")
	@ResponseBody
	public ResponseEntity<String> upload(@RequestBody Help4DevsAwsS3RequestDto help4DevsAwsS3RequestDto) {
		log.info("Request received to add file");
		return new ResponseEntity<>(help4DevsAwsS3Service.saveToS3(help4DevsAwsS3RequestDto), HttpStatus.OK);
	}

	@GetMapping(path = "/api/s3/deprecated/download/{filename}")
	@ResponseBody
	public ResponseEntity<Help4DevsAwsS3ResponseDto> download(@PathVariable String filename) {
		log.info("Request received to read file: {}", filename);
		Help4DevsAwsS3ResponseDto help4DevsAwsS3ResponseDto = new Help4DevsAwsS3ResponseDto();
		help4DevsAwsS3ResponseDto.setFile(help4DevsAwsS3Service.readFromS3(filename));
		return new ResponseEntity<>(help4DevsAwsS3ResponseDto, HttpStatus.OK);
	}

}
