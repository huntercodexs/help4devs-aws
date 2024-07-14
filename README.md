# HELP4DEVS AWS S3 - JAVA
Using AWS Credentials Provider and Endpoint Configuration

### Pre Requisites

- Java 8 / JDK 1.8
- Spring Boot 2.3.7.RELEASE
- spring-cloud-starter-aws
- Properties Details
- Bucket created in the AWS S3

### How to use

- Download and set up the env to run the JDK/JRE 1.8
- Create one project from https://start.spring.io/
- Import the dependencies in the pom.xml

<code>

    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-aws</artifactId>
    </dependency>

</code>

- Create the properties in the application.properties file

<pre>
aws.s3.bucket.name=s3-help4devs-files
cloud.aws.endpoint.uri=http://s3.localhost.localstack.cloud:4566/
cloud.aws.stack.auto=false
cloud.aws.region.static=us-east-1
</pre>

- Create aws credentials file

<pre>
vi ~/.aws/credentials
</pre>

<pre>
[default]
aws_access_key_id = test
aws_secret_access_key = test
</pre>

> NOTE: The access_key and secret_access_key should be configured as test if the endpoint 
> is pointing to localstack

- Create the bucket in the AWS S3 Service

### Run the Unit Tests

<pre>
src/test/java/codexstester/test/unitary/Help4DevsAwsUnitaryTests.java
</pre>

<code>

    package codexstester.test.unitary;
    
    import codexstester.setup.bridge.Help4DevsBridgeTests;
    import com.huntercodexs.demo.dto.Help4DevsAwsS3ResponseDto;
    import com.huntercodexs.demo.services.Help4DevsAwsS3Service;
    import org.junit.Test;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.MediaType;
    import org.springframework.mock.web.MockMultipartFile;
    import org.springframework.web.multipart.MultipartFile;
    
    import java.io.IOException;
    
    import static com.huntercodexs.demo.resources.Help4DevsFileHandlerService.byteFile;
    
    public class Help4DevsAwsUnitaryTests extends Help4DevsBridgeTests {
    
        String filenameS3 = "70b1ca10-8553-433f-ad4c-693150f3d8b5.doc";
        String filepath = "src/main/resources";
        //String fileType = "conf";
        //String fileType = "csv";
        //String fileType = "doc";
        String fileType = "png";
    
        @Autowired
        Help4DevsAwsS3Service help4DevsAwsS3Service;
    
        @Test
        public void sendToS3Test() throws IOException {
            MultipartFile multipartFile = new MockMultipartFile(
                    "file",
                    "attach."+fileType,
                    String.valueOf(MediaType.MULTIPART_FORM_DATA),
                    byteFile(filepath+"/attach."+fileType));
            Help4DevsAwsS3ResponseDto result = help4DevsAwsS3Service.uploadFile(multipartFile);
            System.out.println(result.getFilename());
            System.out.println(result.getMessage());
        }
    
        @Test
        public void readFromS3Test() {
            byte[] result = help4DevsAwsS3Service.downloadFile(filenameS3);
            String file = new String(result);
            System.out.println(file);
        }
    
        @Test
        public void deleteFromS3Test() {
            Help4DevsAwsS3ResponseDto result = help4DevsAwsS3Service.deleteFile(filenameS3);
            System.out.println(result.getFilename());
            System.out.println(result.getMessage());
        }
    
    }

</code>

### Run the Request REST tests

###### UPLOAD REQUEST

<pre>
POST http://localhost:38500/api/s3/upload {form-data=["file": "{FILE}"]}
</pre>

###### DOWNLOAD REQUEST

<pre>
GET http://localhost:38500/api/s3/download/{filename}
</pre>

###### DELETE REQUEST

<pre>
DELETE http://localhost:38500/api/s3/delete/{filename}
</pre>

###### UPLOAD RESPONSE

<pre>
200 OK {
    "filename": "c19944b0-2210-4dde-b0bc-e87586558c2a.pdf",
    "message": "Upload successfully"
}
</pre>

###### DOWNLOAD RESPONSE

<pre>
200 OK 
[ BINARY FILE CONTENT ]
</pre>

###### DELETE RESPONSE
<pre>
200 OK {
    "filename": "c19944b0-2210-4dde-b0bc-e87586558c2a.pdf",
    "message": "File removed successfully"
}
</pre>
