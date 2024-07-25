# HELP4DEVS AWS CORE LAMBDA FUNCTION JAVA
Api Gateway Proxy Request Event

### Pre Requisites

- Java 17 / JDK 17
- aws-lambda-java-core
- aws-lambda-java-events

### How to use

- Download and set up the env to run the JDK/JRE 17
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

- Put the dependency configurations in the pom.xml file

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

- Put the build configurations in the pom.xml file

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
- Give a "Function name", for example: awsLambdaFunctionApiGatewayProxyTest
- Choose the Runtime environment: Java 17 (in this case)
- In Architecture select x86_64
- In "Advanced settings" mark "Enabled function URL"
  - Next, in "Auth type" mark NONE
- Click on "Create function" button
- Now goto Code tab and scroll down until "Runtime settings"
- Click on "Edit" button
- Inform the correct package path to the current java lambda function, for example:

<pre>
com.huntercodexs.demo.lambda.function.Help4DevsAwsCoreLambdaFunction::handleRequest
</pre>

- Click on Save button
- Go back to the Code tab in the current lambda functions
- Seek for "Upload from" button on the right side of the screen
- Choose "Upload a .zip or .jar file"
- Choose the correct jar file and click on Save
- Now, lookup for the "Function URL" and copy it
- Open a POSTMAN (or something like that) to make a simple HTTP REQUEST, for example:

> REQUEST
<pre>
POST https://nrh67hvjhi2s4qhwmte4jxs5fi0soyfc.lambda-url.us-east-1.on.aws/
</pre>

> RESPONSE
<pre>
200 OK {
    "result": true
}
</pre>

> NOTE: If anything were wrong with the permission, check the Configuration tab, in Function URL, 
> Edit button and check if Auth type is NONE

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