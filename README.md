# HELP4DEVS AWS JAVA S3

> WARNING: Please dont use this branch
> - DEPRECATED
> - BUG
> - DOESN'T WORK

### Pre Requisites

- Java 17 / JDK 17
- Spring Boot 3.1.0
- spring-cloud-starter-aws
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

</code>

- Create the properties in the application.properties file

<pre>
bucket.name=s3-help4devs-files
cloud.aws.stack.auto=false
cloud.aws.region.static=us-east-1
cloud.aws.credentials.accessKey={ACCESS-KEY}
cloud.aws.credentials.secretKey={SECRET-KEY}
</pre>

- Create the bucket in the AWS S3 Service
- Run the Unit Tests

<pre>
src/test/java/codexstester/test/unitary/Help4DevsAwsUnitaryTests.java
</pre>

<code>

</code>

- Run the Request REST tests

REQUEST

<pre>
POST http://localhost:35800/service/api/s3/add {"data": "{CONTENT-FILE-BASE64}", "filename": "filename.ext"}
</pre>

RESPONSE

<pre>
202 ACCEPTED {}
</pre>


