package com.huntercodexs.demo.services;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.huntercodexs.demo.client.Help4DevsAwsSdkS3Client;
import com.huntercodexs.demo.dto.Help4DevsAwsS3ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

@Slf4j
@Service
public class Help4DevsAwsSdkS3Service {

    @Value("${aws.s3.bucket.name}")
    String s3BucketName;

    @Autowired
    AmazonS3 s3Client;

    @Autowired
    Help4DevsAwsSdkS3Client help4DevsAwsSdkS3Client;

    public Help4DevsAwsS3ResponseDto urlRequestGenerator(String filename, HttpMethod http) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE,2);

        String url = s3Client.generatePresignedUrl(s3BucketName, filename, cal.getTime(), http).toString();

        Help4DevsAwsS3ResponseDto help4DevsAwsS3ResponseDto = new Help4DevsAwsS3ResponseDto();
        help4DevsAwsS3ResponseDto.setFilename(filename);
        help4DevsAwsS3ResponseDto.setS3url(new String(Base64.getUrlEncoder().encode(url.getBytes())));
        help4DevsAwsS3ResponseDto.setMessage("Url Generated successfully");

        /*TIP: Here can be made any control to guarantee the security in the transaction.
        * for example: generate a hash related to url and save in the database
        *
        * md5Url = md5(url);
        * save(md5Url)
        * */

        return help4DevsAwsS3ResponseDto;

    }

    public Help4DevsAwsS3ResponseDto uploadS3File(MultipartFile multipartFile, String url) {

        String s3Url = new String(Base64.getUrlDecoder().decode(url));
        String fileName = s3Url.split("\\?")[0].split("/")[3];

        try {
            ResponseEntity<?> result = help4DevsAwsSdkS3Client.upload(s3Url, multipartFile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Help4DevsAwsS3ResponseDto help4DevsAwsS3ResponseDto = new Help4DevsAwsS3ResponseDto();
        help4DevsAwsS3ResponseDto.setFilename(fileName);
        help4DevsAwsS3ResponseDto.setMessage("File upload successfully");

        return help4DevsAwsS3ResponseDto;
    }

    public String downloadS3File(String url) {
        String s3Url = new String(Base64.getUrlDecoder().decode(url));
        return help4DevsAwsSdkS3Client.download(s3Url).getBody();
    }

    public Help4DevsAwsS3ResponseDto deleteS3File(String url) {

        String s3Url = new String(Base64.getUrlDecoder().decode(url));
        String fileName = s3Url.split("\\?")[0].split("/")[3];

        help4DevsAwsSdkS3Client.delete(s3Url);

        Help4DevsAwsS3ResponseDto help4DevsAwsS3ResponseDto = new Help4DevsAwsS3ResponseDto();
        help4DevsAwsS3ResponseDto.setFilename(fileName);
        help4DevsAwsS3ResponseDto.setMessage("File deleted successfully");

        return help4DevsAwsS3ResponseDto;
    }

}