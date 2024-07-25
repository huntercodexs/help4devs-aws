# HELP4DEVS AWS CORE LAMBDA JAVA
Api Gateway Proxy

### Pre Requisites

- Java 21 / JDK 21
- aws-lambda-java-core
- aws-lambda-java-events

### How to use

- Download and set up the env to run the JDK/JRE 21
- Import the dependencies in the pom.xml

<code>

        <dependency>
            <groupId>com.amazonaws</groupId>
            <artifactId>aws-lambda-java-core</artifactId>
            <version>1.2.3</version>
        </dependency>
        <dependency>
            <groupId>com.amazonaws</groupId>
            <artifactId>aws-lambda-java-events</artifactId>
            <version>3.11.3</version>
        </dependency>

</code>

- Put the build configurations in the pom.xml file

<code>

      <build>
          <plugins>
              <plugin>
                  <artifactId>maven-surefire-plugin</artifactId>
                  <version>3.2.2</version>
              </plugin>
              <plugin>
                  <groupId>org.apache.maven.plugins</groupId>
                  <artifactId>maven-shade-plugin</artifactId>
                  <version>3.5.1</version>
                  <configuration>
                      <createDependencyReducedPom>false</createDependencyReducedPom>
                      <filters>
                          <filter>
                              <artifact>*:*</artifact>
                              <excludes>
                                  <exclude>module-info.class</exclude>
                                  <exclude>META-INF/*</exclude>
                                  <exclude>META-INF/versions/**</exclude>
                                  <exclude>META-INF/services/**</exclude>
                              </excludes>
                          </filter>
                      </filters>
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
              <plugin>
                  <groupId>org.apache.maven.plugins</groupId>
                  <artifactId>maven-compiler-plugin</artifactId>
                  <version>3.11.0</version>
                  <configuration>
                      <source>${maven.compiler.source}</source>
                      <target>${maven.compiler.target}</target>
                  </configuration>
              </plugin>
          </plugins>
      </build>

</code>

- Build a jar file with maven

<pre>
Maven > mvn clean package
</pre>

- Get access to your AWS Account.
- Goto to lambda function dashboard
- Click on "Create function" button on the top right at the screen
- Choose "Author from scratch - Start with a simple Hello World example"
- Give a "Function name", for example: aws-lambda-java21-demo
- Choose the Runtime environment: Java 21 (in this case)
- In Architecture select x86_64
- In "Advanced settings" mark "Enabled function URL"
  - Next, in "Auth type" mark NONE

This is a policy that should be generated at that moment

<pre>
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "StatementId": "FunctionURLAllowPublicAccess",
      "Effect": "Allow",
      "Principal": "*",
      "Action": "lambda:InvokeFunctionUrl",
      "Resource": "arn:aws:lambda:us-east-1:905418367021:function:aws-lambda-java21-demo",
      "Condition": {
        "StringEquals": {
          "lambda:FunctionUrlAuthType": "NONE"
        }
      }
    }
  ]
</pre>

- Click on "Create function" button
- Now goto Code tab and scroll down until "Runtime settings"
- Click on "Edit" button
- Inform the correct package path to the current java lambda function, for example
    - com.huntercodexs.demo.lambda.Help4DevsAwsCoreLambdaFunction::handleRequest
- Click on save button
- Go back to the Code tab in the current lambda functions
- Seek for "Upload from" button on the right side of the screen
- Choose "Upload a .zip or .jar file"
- Choose the correct jar file and click on Save 
- Now goto Test tab and create a new event to test the lambda function 
  - Use hello-world template for first tests 
  - Let as is the content in the Event JSON because it's not necessary right now
  - Finally, Click on Test button on the Top of currently form

The result must be

<pre>
{
  "statusCode": 200,
  "body": "{\"status\": true}",
  "isBase64Encoded": false
}
</pre>

- Also, you can call this lambda function using a POSTMAN, see the "Run the Request REST tests"

### Run the Unit Tests

<pre>
src/test/java/com/huntercodexs/demo/lambda/Help4DevsAwsCoreLambdaFunctionTest.java
</pre>

<code>

  package com.huntercodexs.demo.lambda;
  
  import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
  import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
  import org.junit.jupiter.api.Assertions;
  import org.junit.jupiter.api.Test;
  
  import java.util.HashMap;
  
  class Help4DevsAwsCoreLambdaFunctionTest {
  
      Help4DevsAwsCoreLambdaFunction handler;
  
      @Test
      public void handleRequestTest() {
          handler = new Help4DevsAwsCoreLambdaFunction();
          APIGatewayProxyRequestEvent request = new APIGatewayProxyRequestEvent();
          request.setHttpMethod("POST");
          request.setHeaders(new HashMap<>());
          request.setBody("{\"test\": true}");
          request.setPath("/api/test");
          APIGatewayProxyResponseEvent result = handler.handleRequest(request, null);
          Assertions.assertEquals("{\"status\": false}",  result.getBody());
      }
  }

</code>

### Run the Request REST tests

###### REQUEST

> NOTE: The URL function is randomly

<pre>
POST https://nrh67hvjhi2s4qhwmte4jxs5fi0soyfc.lambda-url.us-east-1.on.aws/
</pre>

###### RESPONSE

<pre>
200 OK {
    "result": true
}
</pre>

