package com.huntercodexs.demo.controller.rest;

import com.amazonaws.HttpMethod;
import com.huntercodexs.demo.dto.Help4DevsAwsS3RequestDto;
import com.huntercodexs.demo.dto.Help4DevsAwsS3ResponseDto;
import com.huntercodexs.demo.services.Help4DevsAwsSdkS3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Slf4j
@RestController
public class Help4DevsAwsSdkS3RestController {

    @Autowired
    Help4DevsAwsSdkS3Service help4DevsAwsSdkS3Service;

    @PostMapping("/api/s3/v21/generator")
    public ResponseEntity<Help4DevsAwsS3ResponseDto> generator(
            @RequestParam(value = "fileExtension") String fileExtension,
            @RequestParam(value = "operation") String operation,
            @RequestParam(value = "filename") String filenameOverwrite
    ) {
        String filename = UUID.randomUUID()+"." + fileExtension;

        if (filenameOverwrite != null && !filenameOverwrite.isEmpty()) {
            filename = filenameOverwrite;
        }

        HttpMethod httpMethod = switch (operation) {
            case "download" -> HttpMethod.GET;
            case "upload" -> HttpMethod.PUT;
            case "delete" -> HttpMethod.DELETE;
            default -> throw new RuntimeException("Invalid HTTP Method");
        };

        return new ResponseEntity<>(help4DevsAwsSdkS3Service
                .urlRequestGenerator(filename, httpMethod), HttpStatus.OK);
    }

    @PostMapping(value = "/api/s3/v21/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Help4DevsAwsS3ResponseDto> upload(
            @RequestParam(value = "file") MultipartFile multipartFile,
            @RequestParam(value = "url") String url
    ) {
        return new ResponseEntity<>(help4DevsAwsSdkS3Service
                .uploadS3File(multipartFile, url), HttpStatus.OK);
    }

    @PostMapping("/api/s3/v21/download")
    public ResponseEntity<String> download(
            @RequestBody Help4DevsAwsS3RequestDto help4DevsAwsS3RequestDto
    ) {
        return new ResponseEntity<>(help4DevsAwsSdkS3Service
                .downloadS3File(help4DevsAwsS3RequestDto.getS3url()), HttpStatus.OK);
    }

    @PostMapping("/api/s3/v21/delete")
    public ResponseEntity<Help4DevsAwsS3ResponseDto> delete(
            @RequestBody Help4DevsAwsS3RequestDto help4DevsAwsS3RequestDto
    ) {
        return new ResponseEntity<>(help4DevsAwsSdkS3Service
                .deleteS3File(help4DevsAwsS3RequestDto.getS3url()), HttpStatus.OK);
    }

}
