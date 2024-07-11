package com.huntercodexs.demo.controller.rest;

import com.huntercodexs.demo.services.Help4DevsAwsS3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class Help4DevsAwsS3RestController {

    @Autowired
    private Help4DevsAwsS3Service help4DevsAwsS3Service;

    @PostMapping("/api/s3/upload")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file) {
        return new ResponseEntity<>(help4DevsAwsS3Service.uploadFile(file), HttpStatus.OK);
    }

    @GetMapping("/api/s3/download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
        byte[] data = help4DevsAwsS3Service.downloadFile(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

    @DeleteMapping("/api/s3/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        return new ResponseEntity<>(help4DevsAwsS3Service.deleteFile(fileName), HttpStatus.OK);
    }
}
