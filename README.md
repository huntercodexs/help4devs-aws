# HELP4DEVS AWS CORE LAMBDA JAVA
Lambda with S3 Integration - File Processor

### Aws resources related

- S3
- IAM
- LAMBDA
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
- Goto S3 bucket dashboard and click on "Create bucket" button
- Give a "Bucket name", for example: s3-aws-lambda-function-java11-test
- Let as is the rest of the form information
- Click on "Create bucket" button
- Goto IAM dashboard manager and lookup for "Roles", click on it
- Click on "Create role" button at the right top of the screen
  - Choose "AWS service"
  - Service or use case: Lambda
  - Click on Next button
  - Search for AWSLambdaBasicExecutionRole and check it
  - Search for AmazonS3ReadOnlyAccess and check it
  - Click on Next button
  - Give a "Role name", for example: role-s3-aws-lambda-java11-test
  - Click on "Create role" button
- Goto Lambda function dashboard and click on "Create function"
- Give a "Function name", for example: function-aws-lambda-java11-test
- Choose the Runtime environment: Java 11 (in this case)
- In Permissions, select "Use an existing role"
  - Existing role: role-s3-aws-lambda-java11-test
- click on "Create function" button
- Now, click on "Add trigger" on the lambda function just created
  - It will be open one form to configure the trigger
  - Select the target service: S3
  - Choose the bucket: s3/s3-aws-lambda-function-java11-test
  - Select the Event types: PUT
  - Mark the checkbox to accept the acknowledgment
  - Click on Add
  - You must be able to see the S3 Trigger created and linked in the current Lambda Function
- Go back to the Code tab in the current lambda functions
- Seek for "Upload from" button on the right side of the screen
- Choose "Upload a .zip or .jar file"
- Choose the correct jar file and click on Save
- Now goto Code tab and scroll down until "Runtime settings"
- Click on "Edit" button
- Inform the correct package path to the current java lambda function, for example

<pre>
com.huntercodexs.demo.lambda.function.Help4DevsAwsLambdaS3::handleRequest
</pre>

- Click on save button
- Now, you can make some tests to prove that everything is ok
  - Goto the S3 bucket created in this tutorial: s3-aws-lambda-function-java11-test
  - Click on Upload file
  - Select one specific file that should: csv, pdf, txt, jpg, png or gif
  - Click on Upload button
  - Now, check if the S3 Trigger was executed correctly seen the Cloud Watch
    - Log > Log groups
    
If everything was ok, you must be able to the csv details processed by the lambda function 
execute by the S3Event, for example:

<pre>
2024-07-30T01:33:39.303Z  INIT_START Runtime Version: java:11.v43 Runtime Version ARN: arn:aws:lambda:us-east-1::runtime:098eacbe27876b86d184f51eb9aa42ac53a782e06caab1cdc77ec0410ad64de0
	
2024-07-30T01:33:42.472Z  START RequestId: dda7c607-0836-41d4-8f5c-d47194e76e89 Version: $LATEST
	
2024-07-30T01:33:46.527Z  CSV
	
2024-07-30T01:33:46.527Z  File processor STARTED
	
2024-07-30T01:33:46.549Z  Processing File: 1,Jonathan,Winterscale,lwinterscale0@omniture.com,Male,7.188.229.13
	
2024-07-30T01:33:46.549Z  Processing File: 2,Melli,Montford,mmontford1@sogou.com,Female,249.156.87.245
	
2024-07-30T01:33:46.549Z  Processing File: 3,Buck,Dayne,bdayne2@amazon.co.jp,Male,2.237.72.219
	
2024-07-30T01:33:46.549Z  Processing File: 4,Van,Masey,vmasey3@reverbnation.com,Female,237.168.84.87
	
2024-07-30T01:33:46.549Z  Processing File: 5,Karlie,Aldis,kaldis4@discuz.net,Female,119.33.174.40
	
2024-07-30T01:33:46.549Z  Processing File: 6,Neel,Lunge,nlunge5@smh.com.au,Male,112.181.149.217
	
2024-07-30T01:33:46.549Z  Processing File: 7,Haslett,Reddell,hreddell6@mac.com,Male,154.98.29.182
	
2024-07-30T01:33:46.549Z  Processing File: 8,Hank,Growcott,hgrowcott7@latimes.com,Male,16.143.70.62
	
2024-07-30T01:33:46.549Z  Processing File: 9,Birgitta,Rait,brait8@slashdot.org,Genderfluid,39.177.192.162
	
2024-07-30T01:33:46.549Z  Processing File: 10,Adah,Janous,ajanous9@cam.ac.uk,Genderqueer,53.113.14.210
	
2024-07-30T01:33:46.549Z  File processor FINISHED
	
2024-07-30T01:33:46.589Z  END RequestId: dda7c607-0836-41d4-8f5c-d47194e76e89
	
2024-07-30T01:33:46.589Z  REPORT RequestId: dda7c607-0836-41d4-8f5c-d47194e76e89 Duration: 4117.05 ms Billed Duration: 4118 ms Memory Size: 512 MB Max Memory Used: 152 MB Init Duration: 3168.11 
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

> IMPORTANT: When you talk about run the lambda in the aws production, we need keep in our mind that 
> the AWS Credentials should be DefaultAWSCredentialsProviderChain() to use the credentials from the 
> current user and the credentials configured in the IAM 

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
    
    //Example 1
    amazonS3 = client.amazonS3Default();
    //Example 2
    amazonS3 = client.amazonS3Basic("{KEY}", "{SECRET}", "us-east-1");
    //Example 3
    amazonS3 = client.amazonS3Provider("us-east-1");
    //Example 4
    amazonS3 = client.amazonS3EndpointConfig("http://s3.localhost.localstack.cloud:4566/", "us-east-1");
</pre>

Now you can move on the tests file according to information below

<code>
src/test/java/com/huntercodexs/demo/lambda/Help4DevsAwsLambdaS3Test.java
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
    import java.util.List;
    
    import static com.huntercodexs.demo.lambda.Help4DevsAwsLambdaS3Event.eventRecords;
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
    
            List<S3EventNotificationRecord> recordList = eventRecords(s3EventNotification);
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

