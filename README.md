# HELP4DEVS AWS CORE LAMBDA JAVA
Core

### Pre Requisites

- Java 11 / JDK 11
- aws-lambda-java-core
- junit-jupiter
- mockito-junit-jupiter

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
      <groupId>org.mockito</groupId>
      <artifactId>mockito-junit-jupiter</artifactId>
      <version>4.5.1</version>
      <scope>test</scope>
    </dependency>

    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter</artifactId>
      <version>5.8.2</version>
      <scope>test</scope>
    </dependency>

</code>

- Put the build configurations in the pom.xml file

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
- Goto to lambda function dashboard
- Click on "Create function" button on the top right at the screen
- Choose "Author from scratch - Start with a simple Hello World example"
- Give a "Function name", for example: awsCoreLambdaDemoTest
- Choose the Runtime environment: Java 11 (in this case)
- In Architecture select arm64 to save a little bit of money
- Click on "Create function" button
- Now goto Code tab and scroll down until "Runtime settings"
- Click on "Edit" button
- Inform the correct package path to the current java lambda function, for example
    - com.huntercodexs.demo.lambda.Help4DevsAwsCoreLambdaHandlerDemo::handleRequest
- Click on Save button
- Go back to the Code tab in the current lambda functions
- Seek for "Upload from" button on the right side of the screen
- Choose "Upload a .zip or .jar file"
- Choose the correct jar file and click on Save
- Now goto Test tab and create a new event to test the lambda function
    - Use hello-world template for first tests
    - Put the content into Event JSON, for example: testing aws lambda functions
    - Finally, Click on Test button on the Top of currently form

The result must be

<pre>
"TESTING AWS LAMBDA FUNCTIONS"
</pre>

### Run the Unit Tests

<pre>
src/test/java/com/huntercodexs/demo/lambda/Help4DevsAwsLambdaDemoTest.java
</pre>

<code>

    package com.huntercodexs.demo.lambda;
    
    import com.amazonaws.services.lambda.runtime.Context;
    import com.amazonaws.services.lambda.runtime.LambdaLogger;
    import org.junit.jupiter.api.Assertions;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
    import org.junit.jupiter.api.extension.ExtendWith;
    import org.mockito.Mock;
    import org.mockito.junit.jupiter.MockitoExtension;
    import org.mockito.junit.jupiter.MockitoSettings;
    import org.mockito.quality.Strictness;
    
    import static org.mockito.ArgumentMatchers.anyString;
    import static org.mockito.Mockito.doAnswer;
    import static org.mockito.Mockito.when;
    
    @ExtendWith(MockitoExtension.class)
    @MockitoSettings(strictness = Strictness.LENIENT)
    class Help4DevsAwsCoreLambdaHandlerDemoTest {
    
        Help4DevsAwsCoreLambdaHandlerDemo handle;
    
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
    
            handle = new Help4DevsAwsCoreLambdaHandlerDemo();
        }
    
        @Test
        public void handleRequestContextNullTest() {
            handle = new Help4DevsAwsCoreLambdaHandlerDemo();
            Assertions.assertEquals("hello world", handle.handleRequest("Hello World", null));
        }
    
        @Test
        public void handleRequestContextNotNullTest() {
            when(context.getFunctionName()).thenReturn("handleRequest");
            Assertions.assertEquals("HELLO WORLD", handle.handleRequest("Hello World", context));
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

