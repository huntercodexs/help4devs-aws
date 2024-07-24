# HELP4DEVS AWS LAMBDA FUNCTION JAVA
Function

### Pre Requisites

- Java 17 / JDK 17
- spring-cloud-function-web
- spring-cloud-function-adapter-aws

### How to use

- Download and set up the env to run the JDK/JRE 17
- Import the dependencies in the pom.xml

<code>

		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-function-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-function-adapter-aws</artifactId>
		</dependency>

</code>

- Put the build configurations in the pom.xml file

<code>

	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>2022.0.2</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>

</code>

<code>

	<build>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-deploy-plugin</artifactId>
				<configuration>
					<skip>true</skip>
				</configuration>
			</plugin>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<dependencies>
					<dependency>
						<groupId>org.springframework.boot.experimental</groupId>
						<artifactId>spring-boot-thin-layout</artifactId>
						<version>1.0.28.RELEASE</version>
					</dependency>
				</dependencies>
			</plugin>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-shade-plugin</artifactId>
				<version>3.2.4</version>
				<configuration>
					<createDependencyReducedPom>false</createDependencyReducedPom>
					<shadedArtifactAttached>true</shadedArtifactAttached>
					<shadedClassifierName>aws</shadedClassifierName>
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
- Give a "Function name", for example: awsLambdaFunctionDemoTest
- Choose the Runtime environment: Java 17 (in this case)
- In Architecture select x86_64
- Click on "Create function" button
- Now goto Code tab and scroll down until "Runtime settings"
- Click on "Edit" button
- Inform the correct package path to the current java lambda function, for example:

<pre>
org.springframework.cloud.function.adapter.aws.FunctionInvoker::handleRequest
</pre>

or

<pre>
com.huntercodexs.demo.lambda.function.Help4DevsAwsLambdaFunction::reverseSimple
</pre>

- Click on Save button
- Go back to the Code tab in the current lambda functions
- Seek for "Upload from" button on the right side of the screen
- Choose "Upload a .zip or .jar file"
- Choose the correct jar file and click on Save
- Now goto Test tab and create a new event to test the lambda function
    - Use hello-world template for first tests
    - Put the content into Event JSON, for example: "aws java17 lambda function test"
    - Finally, Click on Test button on the Top of currently form

The result must be

<pre>
"tset noitcnuf adbmal 71avaj swa"
</pre>

### Run the Unit Tests

<pre>
src/test/java/com/huntercodexs/demo/lambda/Help4DevsAwsCoreLambdaHandlerDemoTest.java
</pre>

<code>

    package codexstester.test.unitary;
    
    import codexstester.setup.bridge.Help4DevsBridgeTests;
    import com.huntercodexs.demo.lambda.function.Help4DevsAwsLambdaFunction;
    import org.junit.Test;
    
    public class Help4DevsAwsLambdaFunctionUnitaryTests extends Help4DevsBridgeTests {
    
          Help4DevsAwsLambdaFunction function;
      
          @Test
          public void reverseSimpleTest() {
              function = new Help4DevsAwsLambdaFunction();
              System.out.println(function.reverseSimple("abc"));
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