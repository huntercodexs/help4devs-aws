# HELP4DEVS AWS SDK BOM JMS SQS - JAVA
AWS Credentials Provider

### Pre Requisites

- Java 8 / JDK 1.8
- Spring Boot 2.1.6.RELEASE
- aws-java-sdk-sqs
- amazon-sqs-java-messaging-lib
- javax.jms-api
- Properties Details
- Bucket created in the AWS SQS

### How to use

- Download and set up the env to run the JDK/JRE 1.8
- Create one project from https://start.spring.io/
- Import the dependencies in the pom.xml

SDK

<code>

    <dependency>
        <groupId>com.amazonaws</groupId>
        <artifactId>aws-java-sdk-sqs</artifactId>
    </dependency>

</code>


JMS

<code>

    <dependency>
        <groupId>com.amazonaws</groupId>
        <artifactId>amazon-sqs-java-messaging-lib</artifactId>
        <version>1.0.6</version>
    </dependency>

    <dependency>
        <groupId>javax.jms</groupId>
        <artifactId>javax.jms-api</artifactId>
        <version>2.0</version>
    </dependency>

</code>

- Create the dependencies management in the pom.xml

<code>

	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>com.amazonaws</groupId>
				<artifactId>aws-java-sdk-bom</artifactId>
				<version>1.11.379</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>

</code>

- Create the properties in the application.properties file

<pre>
bucket.name=s3-help4devs-files
cloud.aws.stack.auto=false
cloud.aws.region.static=us-east-1
</pre>

- Create aws credentials file

<pre>
[default]
aws_access_key_id = {KEY}
aws_secret_access_key = {SECRET}
</pre>

- Create the bucket in the AWS Sqs Service

### Run the Unit Tests

<pre>
src/test/java/codexstester/test/unitary/Help4DevsAwsSdkBomUnitaryTests.java
</pre>

<code>

    package codexstester.test.unitary;
    
    import codexstester.setup.bridge.Help4DevsBridgeTests;
    import com.huntercodexs.demo.service.Help4DevsAwsSdkBomSqsService;
    import org.junit.Test;
    import org.springframework.beans.factory.annotation.Autowired;
    
    public class Help4DevsAwsSdkBomUnitaryTests extends Help4DevsBridgeTests {
    
        @Autowired
        Help4DevsAwsSdkBomSqsService help4DevsAwsSdkBomSqsService;
    
        @Test
        public void messageProducerTest() {
            help4DevsAwsSdkBomSqsService.createQueue();
            help4DevsAwsSdkBomSqsService.messageProducer("test");
        }
    
        @Test
        public void messageConsumerTest() {
            help4DevsAwsSdkBomSqsService.messageConsumer("test");
        }
    
        @Test
        public void messageEraserTest() {
            help4DevsAwsSdkBomSqsService.messageEraser("test");
            help4DevsAwsSdkBomSqsService.deleteQueue();
        }
    
    }

</code>

<pre>
src/test/java/codexstester/test/unitary/Help4DevsAwsJmsUnitaryTests.java
</pre>

<code>

    package codexstester.test.unitary;
    
    import codexstester.setup.bridge.Help4DevsBridgeTests;
    import com.huntercodexs.demo.service.Help4DevsAwsJmsSqsService;
    import org.junit.Test;
    import org.springframework.beans.factory.annotation.Autowired;
    
    import javax.jms.JMSException;
    
    public class Help4DevsAwsJmsUnitaryTests extends Help4DevsBridgeTests {
    
        @Autowired
        Help4DevsAwsJmsSqsService help4DevsAwsJmsSqsService;
    
        @Test
        public void messageProducerStandardTest() throws JMSException, InterruptedException {
            help4DevsAwsJmsSqsService.messageProducerStandard("test");
        }
    
        @Test
        public void messageConsumerStandardTest() throws JMSException, InterruptedException {
            help4DevsAwsJmsSqsService.messageConsumerStandard();
        }
    
        @Test
        public void messageProducerFifoTest() throws JMSException, InterruptedException {
            help4DevsAwsJmsSqsService.messageProducerFifo("test");
        }
    
        @Test
        public void messageConsumerFifoTest() throws JMSException, InterruptedException {
            help4DevsAwsJmsSqsService.messageConsumerFifo();
        }
    
    }

</code>

### Run the Request REST tests

###### REQUEST

<pre>
</pre>

###### RESPONSE

<pre>
</pre>


