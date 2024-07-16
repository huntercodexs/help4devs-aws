# HELP4DEVS AWS SQS - JAVA
AWS Credentials

### Pre Requisites

- Java 11 / JDK 11
- Spring Boot 2.3.4.RELEASE
- spring-cloud-starter-aws
- spring-cloud-starter-aws-messaging
- Properties Details

> IMPORTANT: Queue created in the AWS SQS before running this project

### How to use

- Download and set up the env to run the JDK/JRE 11
- Create one project from https://start.spring.io/
- Import the dependencies in the pom.xml

<code>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-aws</artifactId>
        </dependency>

		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-aws-messaging</artifactId>
		</dependency>

</code>

- Create the dependencies management in the pom.xml

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

- Create the properties in the application.properties file

<pre>
cloud.aws.account-id={ACCOUNT-ID}
cloud.aws.queue.name={QUEUE-NAME}
cloud.aws.stack.auto=false
cloud.aws.region.static={REGION}
cloud.aws.credentials.accessKey={ACCESS-KEY}
cloud.aws.credentials.secretKey={SECRET-KEY}
</pre>

> IMPORTANT: Inform correctly all information above

### Run the Unit Tests

<pre>
src/test/java/codexstester/test/unitary/Help4DevsAwsSqsUnitaryTests.java
</pre>

<code>

    package codexstester.test.unitary;
    
    import codexstester.setup.bridge.Help4DevsBridgeTests;
    import com.huntercodexs.demo.service.Help4DevsAwsSqsService;
    import org.junit.Test;
    import org.springframework.beans.factory.annotation.Autowired;
    
    public class Help4DevsAwsSqsUnitaryTests extends Help4DevsBridgeTests {
    
        @Autowired
        Help4DevsAwsSqsService help4DevsAwsSqsService;
    
        @Test
        public void messagePublisherTest() {
            help4DevsAwsSqsService.messagePublisher("test");
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

### ABOUT QUEUES

###### ATTRIBUTES

> SOURCE: localstack

<pre>
Attributes
A map of attributes with their corresponding values. The following lists the names, descriptions, and values of the special request parameters that the CreateQueue action uses:

    DelaySeconds – The length of time, in seconds, for which the delivery of all messages in the queue is delayed. Valid values: An integer from 0 to 900 seconds (15 minutes). Default: 0.
    MaximumMessageSize – The limit of how many bytes a message can contain before Amazon SQS rejects it. Valid values: An integer from 1,024 bytes (1 KiB) to 262,144 bytes (256 KiB). Default: 262,144 (256 KiB).
    MessageRetentionPeriod – The length of time, in seconds, for which Amazon SQS retains a message. Valid values: An integer from 60 seconds (1 minute) to 1,209,600 seconds (14 days). Default: 345,600 (4 days).
    Policy – The queue's policy. A valid Amazon Web Services policy. For more information about policy structure, see Overview of Amazon Web Services IAM Policies in the Amazon IAM User Guide.
    ReceiveMessageWaitTimeSeconds – The length of time, in seconds, for which a ReceiveMessage action waits for a message to arrive. Valid values: An integer from 0 to 20 (seconds). Default: 0.
    RedrivePolicy – The string that includes the parameters for the dead-letter queue functionality of the source queue as a JSON object. For more information about the redrive policy and dead-letter queues, see Using Amazon SQS Dead-Letter Queues in the Amazon SQS Developer Guide.
        deadLetterTargetArn – The Amazon Resource Name (ARN) of the dead-letter queue to which Amazon SQS moves messages after the value of maxReceiveCount is exceeded.
        maxReceiveCount – The number of times a message is delivered to the source queue before being moved to the dead-letter queue. When the ReceiveCount for a message exceeds the maxReceiveCount for a queue, Amazon SQS moves the message to the dead-letter-queue.
    The dead-letter queue of a FIFO queue must also be a FIFO queue. Similarly, the dead-letter queue of a standard queue must also be a standard queue.
    VisibilityTimeout – The visibility timeout for the queue, in seconds. Valid values: An integer from 0 to 43,200 (12 hours). Default: 30. For more information about the visibility timeout, see Visibility Timeout in the Amazon SQS Developer Guide.

The following attributes apply only to server-side-encryption:

    KmsMasterKeyId – The ID of an Amazon Web Services managed customer master key (CMK) for Amazon SQS or a custom CMK. For more information, see Key Terms. While the alias of the Amazon Web Services managed CMK for Amazon SQS is always alias/aws/sqs, the alias of a custom CMK can, for example, be alias/MyAlias . For more examples, see KeyId in the Key Management Service API Reference.
    KmsDataKeyReusePeriodSeconds – The length of time, in seconds, for which Amazon SQS can reuse a data key to encrypt or decrypt messages before calling KMS again. An integer representing seconds, between 60 seconds (1 minute) and 86,400 seconds (24 hours). Default: 300 (5 minutes). A shorter time period provides better security but results in more calls to KMS which might incur charges after Free Tier. For more information, see How Does the Data Key Reuse Period Work?.
    SqsManagedSseEnabled – Enables server-side queue encryption using SQS owned encryption keys. Only one server-side encryption option is supported per queue (e.g. SSE-KMS or SSE-SQS).

The following attributes apply only to FIFO (first-in-first-out) queues:

    FifoQueue – Designates a queue as FIFO. Valid values are true and false. If you don't specify the FifoQueue attribute, Amazon SQS creates a standard queue. You can provide this attribute only during queue creation. You can't change it for an existing queue. When you set this attribute, you must also provide the MessageGroupId for your messages explicitly. For more information, see FIFO queue logic in the Amazon SQS Developer Guide.
    ContentBasedDeduplication – Enables content-based deduplication. Valid values are true and false. For more information, see Exactly-once processing in the Amazon SQS Developer Guide. Note the following:
        Every message must have a unique MessageDeduplicationId.
            You may provide a MessageDeduplicationId explicitly.
            If you aren't able to provide a MessageDeduplicationId and you enable ContentBasedDeduplication for your queue, Amazon SQS uses a SHA-256 hash to generate the MessageDeduplicationId using the body of the message (but not the attributes of the message).
            If you don't provide a MessageDeduplicationId and the queue doesn't have ContentBasedDeduplication set, the action fails with an error.
            If the queue has ContentBasedDeduplication set, your MessageDeduplicationId overrides the generated one.
        When ContentBasedDeduplication is in effect, messages with identical content sent within the deduplication interval are treated as duplicates and only one copy of the message is delivered.
        If you send one message with ContentBasedDeduplication enabled and then another message with a MessageDeduplicationId that is the same as the one generated for the first MessageDeduplicationId, the two messages are treated as duplicates and only one copy of the message is delivered.

The following attributes apply only to high throughput for FIFO queues:

    DeduplicationScope – Specifies whether message deduplication occurs at the message group or queue level. Valid values are messageGroup and queue.
    FifoThroughputLimit – Specifies whether the FIFO queue throughput quota applies to the entire queue or per message group. Valid values are perQueue and perMessageGroupId. The perMessageGroupId value is allowed only when the value for DeduplicationScope is messageGroup.

To enable high throughput for FIFO queues, do the following:

    Set DeduplicationScope to messageGroup.
    Set FifoThroughputLimit to perMessageGroupId.

If you set these attributes to anything other than the values shown for enabling high throughput, normal throughput is in effect and deduplication occurs as specified. For information on throughput quotas, see Quotas related to messages in the Amazon SQS Developer Guide.
</pre>

### WARNING

> NOTE: At this moment there is one issue in the project environment that not
> was fixed yet, maybe in the future it will be fixed

<pre>
WARNING: An illegal reflective access operation has occurred
WARNING: Illegal reflective access by org.codehaus.groovy.reflection.CachedClass (file:/home/jereelton/.m2/repository/org/codehaus/groovy/groovy/2.5.13/groovy-2.5.13.jar) to method java.lang.Object.finalize()
WARNING: Please consider reporting this to the maintainers of org.codehaus.groovy.reflection.CachedClass
WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
WARNING: All illegal access operations will be denied in a future release
</pre>

