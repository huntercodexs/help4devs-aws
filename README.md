# HELP4DEVS AWS SDK SQS - JAVA
AWS Credentials Provider

### Pre Requisites

- Java 8 / JDK 1.8
- Spring Boot 2.0.1_RELEASE
- aws-java-sdk
- Properties Details
- Bucket created in the AWS SQS

### How to use

- Download and set up the env to run the JDK/JRE 1.8
- Create one project from https://start.spring.io/
- Import the dependencies in the pom.xml

<code>

    <dependency>
        <groupId>com.amazonaws</groupId>
        <artifactId>aws-java-sdk</artifactId>
        <version>1.11.788</version>
    </dependency>

</code>

- Create the properties in the application.properties file

<pre>
bucket.name=s3-help4devs-files
cloud.aws.stack.auto=false
cloud.aws.region.static=us-east-1
</pre>

- Create aws credentials file

<pre>
vi ~/.aws/credentials
</pre>

<pre>
[default]
aws_access_key_id = {KEY}
aws_secret_access_key = {SECRET}
</pre>

- Create the bucket in the AWS Sqs Service

### Run the Unit Tests

<pre>
src/test/java/codexstester/test/unitary/Help4DevsAwsUnitaryTests.java
</pre>

<code>

    package codexstester.test.unitary;
    
    import codexstester.setup.bridge.Help4DevsBridgeTests;
    import com.huntercodexs.demo.service.Help4DevsAwsSdkSqsService;
    import org.junit.Test;
    import org.springframework.beans.factory.annotation.Autowired;
    
    import java.io.IOException;
    
    public class Help4DevsAwsUnitaryTests extends Help4DevsBridgeTests {
    
        @Autowired
        Help4DevsAwsSdkSqsService help4DevsAwsSdkSqsService;
    
        @Test
        public void createSqsQueueTest() throws IOException {
            help4DevsAwsSdkSqsService.createQueue();
        }
    
        @Test
        public void listSqsQueueTest() throws IOException {
            help4DevsAwsSdkSqsService.listQueue();
        }
    
        @Test
        public void deleteSqsQueueTest() throws IOException {
            help4DevsAwsSdkSqsService.deleteQueue();
        }
    
        @Test
        public void sendMessageTest() {
            String url = "{URL}";
            String message = "test";
            help4DevsAwsSdkSqsService.sendMessage(url, message);
        }
    
        @Test
        public void readMessageTest() {
            String url = "{URL}";
            String message = "test";
            help4DevsAwsSdkSqsService.readMessage(url, message);
        }
    
        @Test
        public void cancelMessageTest() {
            String url = "{URL}";
            String message = "test";
            help4DevsAwsSdkSqsService.cancelMessage(url, message);
        }
    }

</code>

### Run the Request REST tests

###### REQUEST

<pre>
</pre>

###### RESPONSE

<pre>
</pre>


