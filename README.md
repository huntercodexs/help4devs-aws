# HELP4DEVS AWS JAVA LAMBDA
Sample

### Topic

Read books details

> WARNING: This sample project don't use the spring-boot-starter-web, just spring-boot-starter, 
> it means that it will never keep the service or process running up, in other words it just will 
> running at the moment it will be called by any request.

### Pre Requisites

- Java 8 / JDK 1.8
- Spring Boot 2.3.1.RELEASE
- aws-lambda-java-core
- spring-cloud-function-adapter-aws
- aws-lambda-java-events
- Properties Details

### How to use

- Download and set up the env to run the JDK/JRE 1.8
- Create one project from https://start.spring.io/
- Import the dependencies in the pom.xml

<code>

		<dependency>
			<groupId>com.amazonaws</groupId>
			<artifactId>aws-lambda-java-core</artifactId>
			<version>1.1.0</version>
		</dependency>

		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-function-adapter-aws</artifactId>
		</dependency>
		<dependency>
			<groupId>com.amazonaws</groupId>
			<artifactId>aws-lambda-java-events</artifactId>
			<version>2.0.2</version>
		</dependency>

</code>

- Create a spring cloud dependency management

<code>

	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>Hoxton.SR6</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>

</code>

- Create the properties in the application.properties file (optional)

<pre>
cloud.aws.stack.auto=false
cloud.aws.region.static=us-east-1
cloud.aws.credentials.accessKey={ACCESS-KEY}
cloud.aws.credentials.secretKey={SECRET-KEY}
</pre>

- Create a jar file by maven

<pre>
Maven > Lifecycle > install
</pre>

> NOTE: Don't forget to remove the log configuration from the application.properties file

- Get access to your AWS Account.
- Goto to lambda function dashboard
- Click on "Create function" button on the top right at the screen
- Choose "Author from scratch - Start with a simple Hello World example"
- Give a "Function name", for example: bookFunctionTest
- Choose the Runtime environment: Java 8 (in this case)
- In advanced settings choose "Enable function URL"
  - Auth type: NONE

The policy must be something like below

<pre>
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "StatementId": "FunctionURLAllowPublicAccess",
      "Effect": "Allow",
      "Principal": "*",
      "Action": "lambda:InvokeFunctionUrl",
      "Resource": "arn:aws:lambda:us-east-1:905418367021:function:bookFunctionTest",
      "Condition": {
        "StringEquals": {
          "lambda:FunctionUrlAuthType": "NONE"
        }
      }
    }
  ]
}
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

    package codexstester.test.unitary;
    
    import codexstester.setup.bridge.Help4DevsBridgeTests;
    import com.huntercodexs.demo.lambda.model.BookModel;
    import com.huntercodexs.demo.lambda.repository.BookRepository;
    import org.junit.Test;
    import org.springframework.beans.factory.annotation.Autowired;
    
    import java.util.List;
    
    public class Help4DevsAwsSqsUnitaryTests extends Help4DevsBridgeTests {
    
        @Autowired
        BookRepository bookRepository;
    
        @Test
        public void createTest() {
            List<BookModel> result = bookRepository.create();
    
            for (BookModel bookModel : result) {
                System.out.println("Book: " + bookModel.getId());
                System.out.println(bookModel.getName());
                System.out.println(bookModel.getYear());
            }
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

