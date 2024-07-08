package com.huntercodexs.demo.services;

import com.huntercodexs.demo.dto.AwsS3RequestDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

@Slf4j
@Service
public class Help4DevsAwsS3Service {

    @Value("${bucket.name}")
    String bucketName;

    @Autowired
    ResourceLoader resourceLoader;

    public String saveToS3(AwsS3RequestDto awsS3RequestDto) {

        if (awsS3RequestDto.getFilename() == null || awsS3RequestDto.getFilename().isEmpty()) {
            awsS3RequestDto.setFilename(UUID.randomUUID().toString()+".jpg");
        }

        log.info("Starting save in S3");

        String path = "s3://"+bucketName+"/"+ awsS3RequestDto.getFilename();

        log.info("S3 path: {}", path);

        Resource resource = resourceLoader.getResource(path);

        WritableResource writableResource = (WritableResource) resource;

        try (OutputStream outputStream = writableResource.getOutputStream()) {

            outputStream.write(awsS3RequestDto.getData());

            log.info("Image saved successfully in the S3");
            return awsS3RequestDto.getFilename().split("\\.")[0];

        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }

    }

    public String readFromS3(String guid) {

        String path = "s3://"+bucketName+"/"+guid+".jpg";

        log.info("Reading image from S3: {}", path);

        Resource resource = resourceLoader.getResource(path);

        try {

            InputStream inputStream = resource.getInputStream();
            return new String(Base64.getEncoder().encode(IOUtils.toByteArray(inputStream)), StandardCharsets.UTF_8);

        } catch (Exception e) {
            return null;
        }

    }

}
