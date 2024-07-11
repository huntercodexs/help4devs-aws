# HELP4DEVS AWS JAVA S3

### Pre Requisites

- Java 8 / JDK 1.8
- Spring Boot 2.3.7.RELEASE
- spring-cloud-starter-aws
- Properties Details
- Bucket created in the AWS S3

### How to use

- Create the bucket in the AWS S3 Service
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

### Tests

- Run the Unit Tests

<pre>
src/test/java/codexstester/test/unitary/Help4DevsAwsUnitaryTests.java
</pre>

<code>

    package codexstester.test.unitary;

    import codexstester.setup.bridge.Help4DevsBridgeTests;
    import com.huntercodexs.demo.services.Help4DevsAwsS3Service;
    import org.junit.Test;
    import org.springframework.beans.factory.annotation.Autowired;
    
    import java.io.IOException;
    
    public class Help4DevsAwsUnitaryTests extends Help4DevsBridgeTests {
    
        public static String path = "src/test/java/codexstester/setup/datasource";
    
        @Autowired
        Help4DevsAwsS3Service help4DevsAwsS3Service;
    
        @Test
        public void sendToS3Test() throws IOException {
        }
    
        @Test
        public void readFromS3Test() {
        }
    
        @Test
        public void deleteFromS3Test() {
        }
    
    }

</code>

- Run the Request REST tests

UPLOAD REQUEST

<pre>
POST http://localhost:38500/api/s3/upload {form-data=["file": "{FILE}"]}
</pre>

DOWNLOAD REQUEST

<pre>
GET http://localhost:38500/api/s3/download/{filename}
</pre>

DELETE REQUEST

<pre>
DELETE http://localhost:38500/api/s3/delete/{filename}
</pre>

UPLOAD RESPONSE

<pre>
200 OK {
    "filename": "c19944b0-2210-4dde-b0bc-e87586558c2a.pdf",
    "message": "Upload successfully"
}
</pre>

DOWNLOAD RESPONSE

<pre>
200 OK 
[ BINARY FILE CONTENT ]
</pre>

DELETE RESPONSE
<pre>
200 OK {
    "filename": "c19944b0-2210-4dde-b0bc-e87586558c2a.pdf",
    "message": "File removed successfully"
}
</pre>


