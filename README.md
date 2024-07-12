# HELP4DEVS AWS JAVA S3

### Pre Requisites

- Java 17 / JDK17
- Spring Boot 3.0.6
- aws-java-sdk-s3
- Properties Details
- Bucket created in the AWS S3

### How to use

- Download and set up the env to run the JDK/JRE 17
- Create one project from https://start.spring.io/
- Import the dependencies in the pom.xml

<code>

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
cloud.aws.credentials.accessKey=
cloud.aws.credentials.secretKey=
</pre>

- Create the bucket in the AWS S3 Service
- Run the Unit Tests

<pre>
src/test/java/codexstester/test/unitary/Help4DevsAwsUnitaryTests.java
</pre>

<code>

</code>

- Run the Request REST tests

UPLOAD REQUEST

<pre>
POST http://localhost:38500/api/s3/upload {form-data=["file": "{FILE}"]}
</pre>

DOWNLOAD REQUEST

<pre>
GET http://localhost:38500/api/s3/download/{filename}
</pre>

DELETE REQUEST

<pre>
DELETE http://localhost:38500/api/s3/delete/{filename}
</pre>

UPLOAD RESPONSE

<pre>
200 OK {
    "filename": "c19944b0-2210-4dde-b0bc-e87586558c2a.pdf",
    "message": "Upload successfully"
}
</pre>

DOWNLOAD RESPONSE

<pre>
200 OK
[ BINARY FILE CONTENT ]
</pre>

DELETE RESPONSE
<pre>
200 OK {
    "filename": "c19944b0-2210-4dde-b0bc-e87586558c2a.pdf",
    "message": "File removed successfully"
}
</pre>
