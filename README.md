# HELP4DEVS AWS JAVA S3

### Pre Requisites

- Java 8 / JDK 1.8
- Spring Boot 2.0.1_RELEASE
- spring-cloud-starter-aws
- Properties Details
- Bucket created in the AWS S3

### How to use

> NOTE: This S3 implementation convert a binary file to base64 string before save in the bucket 

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
bucket.name=s3-help4devs-files
cloud.aws.stack.auto=false
cloud.aws.region.static=us-east-1
cloud.aws.credentials.accessKey={ACCESS-KEY}
cloud.aws.credentials.secretKey={SECRET-KEY}
</pre>

- Create the bucket in the AWS S3 Service
- Run the Unit Tests

<pre>
src/test/java/codexstester/test/unitary/Help4DevsAwsUnitaryTests.java
</pre>

<code>

    package codexstester.test.unitary;
    
    import codexstester.setup.bridge.Help4DevsBridgeTests;
    import com.huntercodexs.demo.dto.Help4DevsAwsS3RequestDto;
    import com.huntercodexs.demo.services.Help4DevsAwsS3Service;
    import org.junit.Test;
    import org.springframework.beans.factory.annotation.Autowired;
    
    import java.io.IOException;
    import java.nio.charset.StandardCharsets;
    import java.util.Base64;
    
    import static codexstester.setup.datasource.MediaTests.ioFile;
    
    public class Help4DevsAwsUnitaryTests extends Help4DevsBridgeTests {
    
        public static String path = "src/test/java/codexstester/setup/datasource";
    
        @Autowired
        Help4DevsAwsS3Service help4DevsAwsS3Service;
    
        @Test
        public void sendToS3Test() throws IOException {
            AwsS3RequestDto help4DevsAwsS3RequestDto = new AwsS3RequestDto();
            help4DevsAwsS3RequestDto.setFilename("");
            help4DevsAwsS3RequestDto.setData(ioFile(path+"/selfie.txt").getBytes(StandardCharsets.UTF_8));
            System.out.println(help4DevsAwsS3Service.saveToS3(help4DevsAwsS3RequestDto));
        }
    
        @Test
        public void readFromS3Test() {
            String guid = "f4899c1a-5879-42bf-b1d5-0087a2d7aa28";
            byte[] bytes = Base64.getDecoder().decode(help4DevsAwsS3Service.readFromS3(guid));
    
            System.out.println("/*BASE 64*/");
            System.out.println(new String(bytes, StandardCharsets.UTF_8));
    
            System.out.println("/*BINARY*/");
            System.out.println(new String(Base64.getDecoder().decode(bytes), StandardCharsets.UTF_8));
        }
    
    }

</code>

- Run the Request REST tests

REQUEST

<pre>
POST http://localhost:35800/service/api/s3/add {"data": "{CONTENT-FILE-BASE64}", "filename": "filename.ext"}
</pre>

RESPONSE

<pre>
202 ACCEPTED {}
</pre>


