# HELP4DEVS AWS LAMBDA JAVA
Demo

### Pre Requisites

- Java 11 / JDK 11

### How to use

- Download and set up the env to run the JDK/JRE 11
- Import the dependencies in the pom.xml
- Build a jar file with maven

<pre>
Maven > mvn clean package
</pre>

- Get access to your AWS Account.
- Goto to lambda function dashboard
- Click on "Create function" button on the top right at the screen
- Choose "Author from scratch - Start with a simple Hello World example"
- Give a "Function name", for example: awsLambdaDemoTest
- Choose the Runtime environment: Java 11 (in this case)
- In Architecture select arm64 to save a little bit of money
- Click on "Create function" button
- Now goto Code tab and scroll down until "Runtime settings"
- Click on "Edit" button
- Inform the correct package path to the current java lambda function, for example
    - com.huntercodexs.demo.lambda.Help4DevsAwsLambdaDemo::handleRequest
- Click on save button
- Go back to the Code tab in the current lambda functions
- Seek for "Upload from" button on the right side of the screen
- Choose "Upload a .zip or .jar file"
- Choose the correct jar file and click on Save
- Now goto Test tab and create a new event to test the lambda function
    - Use hello-world template for first tests
    - Remove the content from Event JSON
    - Finally, Click on Test button on the Top of currently form

The result must be

<pre>
"Hello, Im an AWS LAMBDA Demo"
</pre>

- Now, repeat this steps for the handleRequestName method.
- For that, goto Code tab and scroll down until "Runtime settings"
- Click on "Edit" button
- Inform the correct package path to the current java lambda function, for example
  - com.huntercodexs.demo.lambda.Help4DevsAwsLambdaDemo::handleRequestName
- Click on save button
- Now goto Test tab and create a new event to test the lambda function
  - Use hello-world template for first tests
  - Put he content "John Smith" in the EventJSON
  - Finally, Click on Test button on the Top of currently form

The result must be

<pre>
"Hello, John Smith"
</pre>

### Run the Unit Tests

<pre>
src/test/java/com/huntercodexs/demo/lambda/Help4DevsAwsLambdaDemoTest.java
</pre>

<code>

    package com.huntercodexs.demo.lambda;
    
    import org.junit.jupiter.api.Test;
    
    import static org.junit.jupiter.api.Assertions.*;
    
    class Help4DevsAwsLambdaDemoTest {
    
        @Test
        void handleRequestTest() {
            Help4DevsAwsLambdaDemo help4DevsAwsLambdaDemo = new Help4DevsAwsLambdaDemo();
            assertEquals("Hello, Im an AWS LAMBDA Demo", help4DevsAwsLambdaDemo.handleRequest());
        }
    
        @Test
        void handlerRequestNameTest() {
            Help4DevsAwsLambdaDemo help4DevsAwsLambdaDemo = new Help4DevsAwsLambdaDemo();
            assertEquals("Hello, Jereelton", help4DevsAwsLambdaDemo.handleRequestName("Jereelton"));
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

