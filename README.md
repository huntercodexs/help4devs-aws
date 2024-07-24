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
- Choose the Runtime environment: Java 8 (in this case)
- In advanced settings choose "Enable function URL"
    - Auth type: NONE

The policy must be something like below

<pre>

</pre>

- Click on "Create function" button
- Now goto Code tab and scroll down until "Runtime settings"
- Click on "Edit" button
- Inform the correct package path to the current java lambda function, for example
    - com.huntercodexs.demo.lambda.handler.BookHandler
- Click on save button
- Now, goto Configuration tab and lookup for "Environment variables"
- Click on Edit button
- Click on "Add environment variable"
- Give the follow information
    - Key: FUNCTION_NAME
    - Value: books
- Click on Save button
- Go back to the Code tab in the current lambda functions
- Seek for "Upload from" button on the right side of the screen
- Choose "Upload a .zip or .jar file"
- Choose the correct jar file and click on Save
- Now goto Test tab and create a new event to test the lambda function
    - Use hello-world template for first tests
    - Give a name for the event test, for example: booksEventTest
    - Remove the content from Event JSON
    - Finally, Click on Test button on the Top of currently form

The result must be

<pre>
[
  {
    "id": 100,
    "name": "Java 8 for all",
    "year": 1990
  },
  {
    "id": 101,
    "name": "Java 17 for all",
    "year": 2000
  },
  {
    "id": 102,
    "name": "Java 21 for all",
    "year": 2018
  }
]
</pre>

- Now, repeat this steps for the booksByName method.
    - Key: FUNCTION_NAME
    - Value: booksByName
    - Use hello-world template for first tests
    - Give a name for the event test, for example: booksEventTest
    - Put the content from Event JSON, for example: "Java 8 for all"
    - Finally, Click on Test button on the Top of currently form

The result must be

<pre>
[
  {
    "id": 100,
    "name": "Java 8 for all",
    "year": 1990
  }
]
</pre>

### Run the Unit Tests

<pre>
src/test/java/codexstester/test/unitary/Help4DevsAwsSqsUnitaryTests.java
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

