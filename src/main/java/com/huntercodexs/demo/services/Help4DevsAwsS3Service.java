package com.huntercodexs.demo.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.huntercodexs.demo.dto.Help4DevsAwsS3ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
public class Help4DevsAwsS3Service {

    @Value("${aws.s3.bucket.name}")
    String s3BucketName;

    @Autowired
    AmazonS3 s3Client;

    private File convertMultiPartFileToFile(MultipartFile file) {

        if (file == null || file.getOriginalFilename() == null) {
            log.error("It is impossible to convert file: {}", file);
            throw new RuntimeException("It is impossible to convert file: " + file);
        }

        File convertedFile = new File(file.getOriginalFilename());

        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipartFile to file: {}", e.getMessage());
            throw new RuntimeException("Error converting multipartFile to file " + e.getMessage());
        }

        return convertedFile;
    }

    public Help4DevsAwsS3ResponseDto uploadFile(MultipartFile multipartFile) {

        File file = convertMultiPartFileToFile(multipartFile);
        String fileType = multipartFile.getOriginalFilename().split("\\.")[1];
        String fileName = UUID.randomUUID() + "." + fileType;

        s3Client.putObject(new PutObjectRequest(s3BucketName, fileName, file));

        Help4DevsAwsS3ResponseDto help4DevsAwsS3ResponseDto = new Help4DevsAwsS3ResponseDto();
        help4DevsAwsS3ResponseDto.setFilename(fileName);
        help4DevsAwsS3ResponseDto.setMessage("Upload successfully");

        if (file.exists() && file.delete()) {
            log.error("File not deleted: {}", file.getAbsolutePath());
        }

        return help4DevsAwsS3ResponseDto;
    }

    public byte[] downloadFile(String fileName) {

        S3Object s3Object = s3Client.getObject(s3BucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();

        try {
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            log.error("Download error: {}", e.getMessage());
        }

        return null;
    }

    public Help4DevsAwsS3ResponseDto deleteFile(String fileName) {
        s3Client.deleteObject(s3BucketName, fileName);
        Help4DevsAwsS3ResponseDto help4DevsAwsS3ResponseDto = new Help4DevsAwsS3ResponseDto();
        help4DevsAwsS3ResponseDto.setFilename(fileName);
        help4DevsAwsS3ResponseDto.setMessage("File removed successfully");
        return help4DevsAwsS3ResponseDto;
    }

}