package com.huntercodexs.demo.controller.rest;

import com.huntercodexs.demo.dto.Help4DevsAwsSdkS3ResponseDto;
import com.huntercodexs.demo.services.Help4DevsAwsSdkS3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class Help4DevsAwsSdkS3RestController {

    @Autowired
    Help4DevsAwsSdkS3Service help4DevsAwsSdkS3Service;

    @PostMapping("/api/s3/v21/upload")
    public ResponseEntity<Help4DevsAwsSdkS3ResponseDto> upload(
            @RequestParam(value = "file") MultipartFile multipartFile
    ) {
        return new ResponseEntity<>(help4DevsAwsSdkS3Service.
                uploadFile(multipartFile), HttpStatus.OK);
    }

    @GetMapping("/api/s3/v21/download/{fileName}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable String fileName) {

        byte[] data = help4DevsAwsSdkS3Service.downloadFile(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);

        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

    @DeleteMapping("/api/s3/v21/delete/{fileName}")
    public ResponseEntity<Help4DevsAwsSdkS3ResponseDto> delete(@PathVariable String fileName) {
        return new ResponseEntity<>(help4DevsAwsSdkS3Service.deleteFile(fileName), HttpStatus.OK);
    }

}
