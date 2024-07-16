# HELP4DEVS AWS JAVA S3

> WARNING: Please don't use this branch
> - DEPRECATED
> - DOESN'T WORK
> - CONTAIN BUG

### Pre Requisites

- Java 17 / JDK 17
- Spring Boot 3.0.6
- spring-cloud-starter-aws
- jaxb-api
- Properties Details
- Bucket created in the AWS S3

### How to use

- Download and set up the env to run the JDK/JRE 17
- Create one project from https://start.spring.io/
- Import the dependencies in the pom.xml

<code>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-aws</artifactId>
        </dependency>

		<dependency>
			<groupId>javax.xml.bind</groupId>
			<artifactId>jaxb-api</artifactId>
			<version>2.3.0</version>
		</dependency>

</code>

- Create the properties in the application.properties file

<pre>
bucket.name=s3-help4devs-files
cloud.aws.stack.auto=false
cloud.aws.region.static=us-east-1
cloud.aws.credentials.accessKey={KEY}
cloud.aws.credentials.secretKey={KEY}
</pre>

- Create the bucket in the AWS S3 Service

### Run the Unit Tests

<pre>
src/test/java/codexstester/test/unitary/Help4DevsAwsUnitaryTests.java
</pre>

<code>

</code>

### Run the Request REST tests

#### UPLOAD

###### REQUEST

<pre>
POST http://localhost:38500/api/s3/basic/upload {"data": "{CONTENT-FILE-BASE64}", "filename": "filename.ext"}
</pre>

###### RESPONSE

<pre>
200 OK {}
</pre>

#### DOWNLOAD

###### REQUEST

<pre>
GET http://localhost:38500/api/s3/basic/download/{filename}
</pre>

###### RESPONSE

<pre>
200 OK {[BINARY-FILE]}
</pre>

