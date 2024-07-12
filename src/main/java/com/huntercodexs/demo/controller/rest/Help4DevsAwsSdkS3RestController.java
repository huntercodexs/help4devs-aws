package com.huntercodexs.demo.controller.rest;

import com.amazonaws.HttpMethod;
import com.huntercodexs.demo.dto.Help4DevsAwsS3ResponseDto;
import com.huntercodexs.demo.services.Help4DevsAwsSdkS3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Slf4j
@RestController
public class Help4DevsAwsSdkS3RestController {

    @Autowired
    Help4DevsAwsSdkS3Service help4DevsAwsSdkS3Service;

    @PostMapping("/api/s3/v1/generator")
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

        return new ResponseEntity<>(
                help4DevsAwsSdkS3Service.urlRequestGenerator(filename, httpMethod),
                HttpStatus.OK);
    }

    @PostMapping("/api/s3/v1/upload")
    public ResponseEntity<Help4DevsAwsS3ResponseDto> upload(
            @RequestParam(value = "file") MultipartFile multipartFile,
            @RequestParam(value = "url") String url
    ) {
        return new ResponseEntity<>(help4DevsAwsSdkS3Service.uploadS3File(multipartFile, url), HttpStatus.OK);
    }

    @PostMapping("/api/s3/v1/download/{filename}")
    public ResponseEntity<String> download(
            @PathVariable(value = "filename") String filename,
            @RequestParam(value = "url") String url
    ) {
        return new ResponseEntity<>(help4DevsAwsSdkS3Service.downloadS3File(filename, url), HttpStatus.OK);
    }

    @PostMapping("/api/s3/v1/delete/{filename}")
    public ResponseEntity<Help4DevsAwsS3ResponseDto> delete(
            @PathVariable(value = "filename") String filename,
            @RequestParam(value = "url") String url
    ) {
        return new ResponseEntity<>(help4DevsAwsSdkS3Service.deleteS3File(filename, url), HttpStatus.OK);
    }

}
