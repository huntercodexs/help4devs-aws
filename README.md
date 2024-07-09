# HELP4DEVS AWS JAVA
A simple repository to serve various functions and methods made in Java

<br /><br />

# Summary

The content that you will find out in this project are:

- <a href="#s3">S3</a>
- <a href="#lambda">Lambda</a>

<br /><br />

# S3


<br /><br />

# LAMBDA

> NOTE: Check the pom.xml before generate the jar file to deploy in the AWS Lambda functions

- Request via URL

You can execute the lambda function using the "Function URL" from the target lambda function in the AWS services, 
for example 

REQUEST
<pre>
POST https://fxwsvhqmdksobfcmov3extc6tm0kzngq.lambda-url.us-east-1.on.aws/
</pre>

RESPONSE
<pre>
{
    "result": true
}
</pre>

