# HELP4DEVS AWS SDK S3 - JAVA
Using AWS Credentials Provider

### Pre Requisites

- Java 17 / JDK17
- Spring Boot 3.0.6
- aws-java-sdk-s3
- jaxb-api
- Properties Details
- Bucket created in the AWS S3

### How to use

- Download and set up the env to run the JDK/JRE 17
- Create one project from https://start.spring.io/
- Import the dependencies in the pom.xml

<code>

    <!--JAVAXB-->
    <!-- https://mvnrepository.com/artifact/javax.xml.bind/jaxb-api -->
    <dependency>
        <groupId>javax.xml.bind</groupId>
        <artifactId>jaxb-api</artifactId>
        <version>2.3.0</version>
    </dependency>

    <!--AWS SDK S3-->
    <dependency>
        <groupId>com.amazonaws</groupId>
        <artifactId>aws-java-sdk-s3</artifactId>
        <version>1.11.163</version>
    </dependency>

</code>

- Create the properties in the application.properties file

<pre>
bucket.name=s3-help4devs-files
cloud.aws.stack.auto=false
cloud.aws.region.static=us-east-1
</pre>

- Create aws credentials file

<pre>
vi ~/.aws/credentials
</pre>

<pre>
[default]
aws_access_key_id = {KEY}
aws_secret_access_key = {SECRET}
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
POST http://localhost:38500/api/s3/beta/upload {"file": [MULTI-PART-FILE]}
</pre>

###### RESPONSE

<pre>
200 OK {
    "filename": "d6ea14b3-d840-4939-b923-dd60ea43a151.png",
    "message": "Upload successfully"
}
</pre>

#### DOWNLOAD

###### REQUEST

<pre>
GET http://localhost:38500/api/s3/beta/download/d6ea14b3-d840-4939-b923-dd60ea43a151.png
</pre>

###### RESPONSE

<pre>
200 OK {BINARY-FILE}
</pre>

#### DELETE

###### REQUEST

<pre>
DELETE http://localhost:38500/api/s3/beta/delete/d6ea14b3-d840-4939-b923-dd60ea43a151.png
</pre>

###### RESPONSE

<pre>
200 OK {
    "filename": "d6ea14b3-d840-4939-b923-dd60ea43a151.png",
    "message": "File removed successfully"
}
</pre>

