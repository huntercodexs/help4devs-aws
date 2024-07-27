# HELP4DEVS AWS CORE LAMBDA JAVA
Lambda with SNS Integration

### Aws resources related

- LAMBDA
- SNS
- Cloud Watch

### Pre Requisites

- Java 11 / JDK 11
- aws-lambda-java-core
- aws-lambda-java-events

### How to use

- Download and set up the env to run the JDK/JRE 11
- Import the dependencies in the pom.xml

<code>

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

<pre>
src/test/java/com/huntercodexs/demo/lambda/Help4DevsAwsCoreLambdaSnsIntegrationDemoTest.java
</pre>

<code>

    package com.huntercodexs.demo.lambda;
    
    import com.amazonaws.services.lambda.runtime.Context;
    import com.amazonaws.services.lambda.runtime.LambdaLogger;
    import com.amazonaws.services.lambda.runtime.events.SNSEvent;
    import com.huntercodexs.demo.lambda.function.Help4DevsAwsCoreLambdaSnsIntegrationDemo;
    import com.huntercodexs.demo.lambda.model.Response;
    import org.junit.jupiter.api.Assertions;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
    import org.junit.jupiter.api.extension.ExtendWith;
    import org.mockito.Mock;
    import org.mockito.junit.jupiter.MockitoExtension;
    import org.mockito.junit.jupiter.MockitoSettings;
    import org.mockito.quality.Strictness;
    
    import java.util.ArrayList;
    import java.util.List;
    
    import static org.mockito.ArgumentMatchers.anyString;
    import static org.mockito.Mockito.doAnswer;
    import static org.mockito.Mockito.when;
    
    @ExtendWith(MockitoExtension.class)
    @MockitoSettings(strictness = Strictness.LENIENT)
    class Help4DevsAwsCoreLambdaSnsIntegrationDemoTest {
    
          Help4DevsAwsCoreLambdaSnsIntegrationDemo handler;
      
          @Mock
          Context context;
      
          @Mock
          LambdaLogger lambdaLogger;
      
          @BeforeEach
          public void setup() {
      
              when(context.getLogger()).thenReturn(lambdaLogger);
      
              doAnswer(call -> {
                  System.out.println((String) call.getArgument(0));
                  return null;
              }).when(lambdaLogger).log(anyString());
      
              handler = new Help4DevsAwsCoreLambdaSnsIntegrationDemo();
          }
      
          @Test
          public void fakerTest() {
              System.out.println("Application is ok");
          }
      
          @Test
          public void handleRequestContextNullTest() {
              handler = new Help4DevsAwsCoreLambdaSnsIntegrationDemo();
              Response result = handler.handleRequest(new SNSEvent(), null);
      
              Assertions.assertEquals(result.getHttpCode(), 400);
              Assertions.assertEquals(result.getMessage(), "Context is null");
          }
      
          @Test
          public void handleRequestContextNotNullTest() {
              handler = new Help4DevsAwsCoreLambdaSnsIntegrationDemo();
      
              List<SNSEvent.SNSRecord> snsRecords = new ArrayList<>();
              SNSEvent snsEvent = new SNSEvent();
              snsEvent.setRecords(snsRecords);
      
              Response result = handler.handleRequest(snsEvent, context);
      
              Assertions.assertEquals(result.getHttpCode(), 200);
              Assertions.assertEquals(result.getMessage(), "OK");
          }
      
          @Test
          public void handleRequestContextNotNullAndSNSEventNullTest() {
              handler = new Help4DevsAwsCoreLambdaSnsIntegrationDemo();
              Response result = handler.handleRequest(new SNSEvent(), context);
      
              Assertions.assertEquals(result.getHttpCode(), 500);
              Assertions.assertEquals(result.getMessage(), "Internal Error");
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

