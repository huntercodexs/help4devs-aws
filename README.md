# HELP4DEVS AWS JAVA LAMBDA

# How to use

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

