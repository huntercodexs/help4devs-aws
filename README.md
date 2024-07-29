# HELP4DEVS AWS CORE LAMBDA JAVA
Lambda with S3 Integration - File Processor

### Aws resources related

- LAMBDA
- S3
- Cloud Watch

### Pre Requisites

- Java 11 / JDK 11
- aws-lambda-java-core
- aws-lambda-java-events
- aws-java-sdk-s3

### How to use

- Download and set up the env to run the JDK/JRE 11
- Import the dependencies in the pom.xml

<code>

        <!--AWS LAMBDA-->
        <dependency>
            <groupId>com.amazonaws</groupId>
            <artifactId>aws-lambda-java-core</artifactId>
            <version>1.2.1</version>
        </dependency>
        <dependency>
            <groupId>com.amazonaws</groupId>
            <artifactId>aws-lambda-java-events</artifactId>
            <version>3.11.0</version>
        </dependency>

        <!--AWS S3 SDK-->
        <dependency>
            <groupId>com.amazonaws</groupId>
            <artifactId>aws-java-sdk-s3</artifactId>
            <version>1.12.153</version>
        </dependency>

</code>

- Configure the maven plugins

<code>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-shade-plugin</artifactId>
                <version>3.2.4</version>
                <configuration>
                    <createDependencyReducedPom>false</createDependencyReducedPom>
                </configuration>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals>
                            <goal>shade</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>

</code>

- Build a jar file with maven

<pre>
Maven > mvn clean package
</pre>

- Get access to your AWS Account.
- Goto to SNS dashboard
- Click on "Create topic" button on the top right at the screen
- Choose "Standard"
- Give a "Topic name", for example: aws-sns-topic-java11-test
- Let as is the rest of the form
- Click on "Create Topic" button
- Now, goto Lambda function dashboard and click on "Create function"
- Give a "Function name", for example: aws-lambda-function-java11-test
- Choose the Runtime environment: Java 11 (in this case)
- Let as is the rest of information in the form
- click on "Create function" button
- Go back to the Code tab in the current lambda functions
- Seek for "Upload from" button on the right side of the screen
- Choose "Upload a .zip or .jar file"
- Choose the correct jar file and click on Save
- Now goto Code tab and scroll down until "Runtime settings"
- Click on "Edit" button
- Inform the correct package path to the current java lambda function, for example

<pre>
com.huntercodexs.demo.lambda.function.Help4DevsAwsCoreLambdaSnsIntegrationDemo::handleRequest
</pre>

- Click on save button
- The next step is to create a subscription in the SNS topic created previously
- So, goto SNS topic and click on "Create subscription"
    - Select the target "Topic ARN"
    - Select the Protocol: AWS Lambda
    - Select the target "Endpoint" for the current subscription
    - Click on "Create subscription"
    - The subscription is done
- Now, refresh the page (press Ctrl+F5) where the target Lambda function is open
    - You must be able to see one trigger called SNS
    - If you click in the SNS trigger, the "Configuration" tab will be open automatically with all information about it
- Go back to SNS topic, and lookup for "Publish message" button and click on it
- Fill the form and click on "Publish message"
- Finally, goto Cloud Watch
    - Log Groups
        - lookup for the message that you have been published

For example

<pre>
{
    "id": 1,
    "name": "Production name here",
    "description": "Production description here"
}
</pre>

The result should be something like below

<pre>
	
2024-07-27T21:04:21.577Z  Product Object: Product(id=1, name=Production name here, description=Production description here) 
</pre>

### Run the Unit Tests

> IMPORTANT: To make units tests you need to configure the s3-event-test.json file placed 
> in the folder resources at this repository, see the example below:

<pre>
{
  "Records": [
    {
      "eventVersion": "2.0",
      "eventSource": "aws:s3",
      "awsRegion": "{AWS-REGION-HERE}",
      "eventTime": "1970-01-01T00:00:00.000Z",
      "eventName": "ObjectCreated:Put",
      "userIdentity": {
        "principalId": "{SAMPLE-ID-HERE}"
      },
      "requestParameters": {
        "sourceIPAddress": "127.0.0.1"
      },
      "responseElements": {
        "x-amz-request-id": "79104EXAMPLEB723",
        "x-amz-id-2": "IOWQ4fDEXAMPLEQM+ey7N9WgVhSnQ6JEXAMPLEZb7hSQDASK+Jd1vEXAMPLEa3Km"
      },
      "s3": {
        "s3SchemaVersion": "1.0",
        "configurationId": "testConfigRule",
        "bucket": {
          "name": "{BUCKET-NAME-HERE}",
          "ownerIdentity": {
            "principalId": "EXAMPLE"
          },
          "arn": "arn:aws:s3:::bucket_name"
        },
        "object": {
          "key": "{FILENAME-HERE.EXTENSION}",
          "size": 5065717,
          "eTag": "c2d226b2e97bec9265eb7e59d2dfac41"
        }
      }
    }
  ]
}
</pre>

Other point to discuss is about credentials, you can use four kind of authentication in the Amazon S3, 
and for that see the examples below:

- Example 1: Default

<code>

      public AmazonS3 amazonS3Default() {
  
          return AmazonS3Client.builder()
                  .withCredentials(new DefaultAWSCredentialsProviderChain())
                  .build();
  
      }

</code>

- Example 2: Basic

<code>

      public AmazonS3 amazonS3Basic(String accessKey, String secretKey, String region) {
  
          if (region == null || region.isEmpty()) {
              region = "us-east-1";
          }
  
          AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
  
          return AmazonS3ClientBuilder.standard()
                  .withCredentials(new AWSStaticCredentialsProvider(credentials))
                  .withRegion(region)
                  .build();
      }

</code>

- Example 3: Provider

<code>

      public AmazonS3 amazonS3Provider(String region) {
  
          if (region == null || region.isEmpty()) {
              region = "us-east-1";
          }
  
          AWSCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
  
          return AmazonS3ClientBuilder.standard()
                  .withCredentials(credentialsProvider)
                  .withRegion(region)
                  .build();
  
      }

</code>

- Example 4: Provider + Endpoint Configuration

<code>

      public AmazonS3 amazonS3EndpointConfig(String endpointUri, String region) {
  
          if (region == null || region.isEmpty()) {
              region = "us-east-1";
          }
  
          if (endpointUri == null || endpointUri.isEmpty()) {
              endpointUri = "s3.amazonaws.com";
          }
  
          AwsClientBuilder.EndpointConfiguration endpointConfig = new AwsClientBuilder.EndpointConfiguration(
                  endpointUri,
                  region
          );
  
          AWSCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
  
          return AmazonS3ClientBuilder.standard()
                  .withCredentials(credentialsProvider)
                  .withEndpointConfiguration(endpointConfig)
                  .build();
  
      }

</code>

In the last case, you can use the endpoint configuration to use localstack as development environment, for example:

<pre>
http://s3.localhost.localstack.cloud:4566/
</pre>

Examples to get connection

<pre>
    Help4DevsAwsS3Client client = new Help4DevsAwsS3Client();
    AmazonS3 amazonS3;
    
    amazonS3 = client.amazonS3Default();
    amazonS3 = client.amazonS3Basic("{KEY}", "{SECRET}", "us-east-1");
    amazonS3 = client.amazonS3Provider("us-east-1");
    amazonS3 = client.amazonS3EndpointConfig("http://s3.localhost.localstack.cloud:4566/", "us-east-1");
</pre>

Now you can move on the tests file according to information below

<code>
src/test/java/com/huntercodexs/demo/lambda/Help4DevsAwsCoreLambdaSnsIntegrationDemoTest.java
</code>

<code>

    package com.huntercodexs.demo.lambda;
    
    import com.amazonaws.services.lambda.runtime.Context;
    import com.amazonaws.services.lambda.runtime.LambdaLogger;
    import com.amazonaws.services.lambda.runtime.events.S3Event;
    import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification.S3EventNotificationRecord;
    import com.amazonaws.services.s3.event.S3EventNotification;
    import com.fasterxml.jackson.databind.ObjectMapper;
    import com.huntercodexs.demo.lambda.function.Help4DevsAwsLambdaS3;
    import org.junit.jupiter.api.Assertions;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
    import org.junit.jupiter.api.extension.ExtendWith;
    import org.mockito.Mock;
    import org.mockito.junit.jupiter.MockitoExtension;
    import org.mockito.junit.jupiter.MockitoSettings;
    import org.mockito.quality.Strictness;
    
    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.List;
    
    import static org.mockito.ArgumentMatchers.anyString;
    import static org.mockito.Mockito.doAnswer;
    import static org.mockito.Mockito.when;
    
    @ExtendWith(MockitoExtension.class)
    @MockitoSettings(strictness = Strictness.LENIENT)
    class Help4DevsAwsLambdaS3Test {
    
        Help4DevsAwsLambdaS3 handler;
    
        @Mock
        Context context;
    
        @Mock
        S3Event s3Event;
    
        @Mock
        LambdaLogger lambdaLogger;
    
        @BeforeEach
        public void setup() {
    
            when(context.getLogger()).thenReturn(lambdaLogger);
    
            doAnswer(call -> {
                System.out.println((String) call.getArgument(0));
                return null;
            }).when(lambdaLogger).log(anyString());
    
            handler = new Help4DevsAwsLambdaS3();
        }
    
        private static List<S3EventNotificationRecord> getS3EventNotificationRecords(
                S3EventNotification s3EventNotification
        ) {
            List<S3EventNotificationRecord> recordList = new ArrayList<>();
    
            S3EventNotificationRecord records = new S3EventNotificationRecord(
                    s3EventNotification.getRecords().get(0).getAwsRegion(),
                    s3EventNotification.getRecords().get(0).getEventName(),
                    s3EventNotification.getRecords().get(0).getEventSource(),
                    String.valueOf(s3EventNotification.getRecords().get(0).getEventTime()),
                    s3EventNotification.getRecords().get(0).getEventVersion(),
                    new com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification.RequestParametersEntity(String.valueOf(s3EventNotification.getRecords().get(0).getRequestParameters())),
                    new com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification.ResponseElementsEntity(String.valueOf(s3EventNotification.getRecords().get(0).getResponseElements().getxAmzId2()), String.valueOf(s3EventNotification.getRecords().get(0).getResponseElements().getxAmzRequestId())),
                    new com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification.S3Entity(
                            String.valueOf(s3EventNotification.getRecords().get(0).getS3().getConfigurationId()),
                            new com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification.S3BucketEntity(
                                    s3EventNotification.getRecords().get(0).getS3().getBucket().getName(),
                                    new com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification.UserIdentityEntity(String.valueOf(s3EventNotification.getRecords().get(0).getS3().getBucket().getOwnerIdentity().getPrincipalId())),
                                    s3EventNotification.getRecords().get(0).getS3().getBucket().getArn()),
                            new com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification.S3ObjectEntity(
                                    String.valueOf(s3EventNotification.getRecords().get(0).getS3().getObject().getKey()),
                                    s3EventNotification.getRecords().get(0).getS3().getObject().getSizeAsLong(),
                                    s3EventNotification.getRecords().get(0).getS3().getObject().geteTag(),
                                    s3EventNotification.getRecords().get(0).getS3().getObject().getVersionId(),
                                    s3EventNotification.getRecords().get(0).getS3().getObject().getSequencer()),
                            ""
                    ),
                    new com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification.UserIdentityEntity(
                            String.valueOf(s3EventNotification.getRecords().get(0).getS3().getBucket().getOwnerIdentity().getPrincipalId())
                    )
            );
    
            recordList.add(records);
            return recordList;
        }
    
        @Test
        public void fakerTest() {
            System.out.println("Application is ok");
        }
    
        @Test
        public void handleRequestContextNullTest() {
            handler = new Help4DevsAwsLambdaS3();
            Boolean result = handler.handleRequest(s3Event, null);
            Assertions.assertFalse(result);
        }
    
        @Test
        public void handleRequestContextNotNullAndS3EventNullTest() {
            handler = new Help4DevsAwsLambdaS3();
            Boolean result = handler.handleRequest(null, context);
            Assertions.assertFalse(result);
        }
    
        @Test
        public void handleRequestS3RecordsIsEmptyTest() {
            handler = new Help4DevsAwsLambdaS3();
            Boolean result = handler.handleRequest(new S3Event(), context);
            Assertions.assertFalse(result);
        }
    
        @Test
        public void handleRequestContextNotNullAndS3EventNotNullTest() throws IOException {
            handler = new Help4DevsAwsLambdaS3();
    
            ObjectMapper mapper = new ObjectMapper();
    
            S3EventNotification s3EventNotification = mapper.readValue(
                    Help4DevsAwsLambdaS3Test.class.getResource("/s3-event-test.json"),
                    S3EventNotification.class);
    
            List<S3EventNotificationRecord> recordList = getS3EventNotificationRecords(s3EventNotification);
            s3Event = new S3Event(recordList);
            Boolean result = handler.handleRequest(s3Event, context);
            Assertions.assertTrue(result);
        }
    
    }

</code>

### Run the Request REST tests

###### REQUEST

<pre>
Unavailable
</pre>

###### RESPONSE

<pre>
Unavailable
</pre>

