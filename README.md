# HELP4DEVS AWS SDK S3 - JAVA
Legacy

### Pre Requisites

- Java 21 / JDK21
- Spring Boot 3.2.1
- aws-java-sdk-s3
- Properties Details
- Bucket created in the AWS S3

### How to use

This project uses the "generatePresignedUrl" from AmazonS3 to generate an url before the final request, for example, 
If you need to send a file for S3 bucket, you must use the HTTP METHOD PUT, if you need to remove the file sent for S3 
you must use the HTTP METHOD DELETE, and so on.

Also in the generator process URL its required to inform the type of the file that wil be sent in the future 
HTTP request, for example: jpg, gif, png, pdf, doc, etc...

- Download and set up the env to run the JDK/JRE 21
- Create one project from https://start.spring.io/
- Import the dependencies in the pom.xml

<code>

    <dependency>
        <groupId>com.amazonaws</groupId>
        <artifactId>aws-java-sdk-s3</artifactId>
        <version>1.11.792</version>
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

###### REQUEST GENERATOR

<pre>
POST http://localhost:38500/api/s3/v21/generator?fileExtension=doc&operation=upload&filename=
</pre>

###### REQUEST

<pre>
POST http://localhost:38500/api/s3/v21/upload {"file": {MULTI-PART-FILE}, "url": "aHR0cHM6Ly9zMy1oZWxwNGR..."}
</pre>

###### RESPONSE

<pre>
200 OK {
    "filename": "e764523d-de94-4297-b522-c466a68c98ba.png",
    "message": "Url Generated successfully",
    "s3url": "aHR0cHM6Ly9zMy1oZWxwNGR..."
}
</pre>

#### DOWNLOAD

###### REQUEST GENERATOR

<pre>
POST http://localhost:38500/api/s3/v21/generator?fileExtension=doc&operation=download&filename=e764523d-de94-4297-b522-c466a68c98ba.doc
</pre>

###### REQUEST

<pre>
POST http://localhost:38500/api/s3/v21/download {"url": "aHR0cHM6Ly9zMy1oZWxwNGR..."}
</pre>

###### RESPONSE

<pre>
200 OK {BINARY-FILE}
</pre>

#### DELETE

###### REQUEST GENERATOR

<pre>
POST http://localhost:38500/api/s3/v21/generator?fileExtension=doc&operation=delete&filename=e764523d-de94-4297-b522-c466a68c98ba.doc
</pre>

###### REQUEST

<pre>
POST http://localhost:38500/api/s3/v21/delete {"url": "aHR0cHM6Ly9zMy1oZWxwNGR..."}
</pre>

###### RESPONSE

<pre>
200 OK {
    "filename": "e764523d-de94-4297-b522-c466a68c98ba.png",
    "message": "File deleted successfully"
}
</pre>

