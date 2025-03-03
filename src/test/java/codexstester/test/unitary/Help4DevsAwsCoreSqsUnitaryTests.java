package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.services.Help4DevsAwsSdkSqsService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class Help4DevsAwsCoreSqsUnitaryTests extends Help4DevsBridgeTests {

    @Autowired
    Help4DevsAwsSdkSqsService help4DevsAwsSdkSqsService;

    /**
     * # SQS
     * <br>
     * QueueName: {MY-QUEUE-NAME}
     * <br>
     * QueueType: FIFO
     * <br>
     * QueueURL: https://sqs.us-east-1.amazonaws.com/{ACCOUNT-NUMBER}/{MY-QUEUE-NAME}
     * <br>
     * QueueARN: arn:aws:sqs:us-east-1:{ACCOUNT-NUMBER}:{MY-QUEUE-NAME}
     * <br>
     * QueueDetails: [Content-based deduplication,High throughput FIFO queue (recommended)]
     * <br>
     * QueuePolicy:
     * <blockquote>
     * <pre>
     * {
     *   "Version": "2012-10-17",
     *   "Id": "__default_policy_ID",
     *   "Statement": [
     *     {
     *       "Effect": "Allow",
     *       "Principal": {
     *         "AWS": "arn:aws:iam::{ACCOUNT-NUMBER}:role/{MY-IAM-ROLE-NAME}"
     *       },
     *       "Action": "SQS:*",
     *       "Resource": "arn:aws:sqs:us-east-1:{ACCOUNT-NUMBER}:{MY-QUEUE-NAME}"
     *     }
     *   ]
     * }
     * </pre>
     * </blockquote>
     * # IAM
     * <br>
     * RoleName: {MY-IAM-ROLE-NAME}
     * <br>
     * RoleTrustRelationships:
     * <blockquote>
     * <pre>
     * {
     *     "Version": "2012-10-17",
     *     "Statement": [
     *         {
     *             "Sid": "Statement1",
     *             "Effect": "Allow",
     *             "Principal": {
     *                 "AWS": "*"
     *             },
     *             "Action": "sts:AssumeRole"
     *         }
     *     ]
     * }
     * </pre>
     * </blockquote>
     * RolePermissions: [AmazonSQSFullAccess (AWS managed), SqsTestPolicy (Custom)]
     * <br>
     * AmazonSQSFullAccess:
     * <blockquote>
     * <pre>
     * {
     *     "Version": "2012-10-17",
     *     "Statement": [
     *         {
     *             "Action": [
     *                 "sqs:*"
     *             ],
     *             "Effect": "Allow",
     *             "Resource": "*"
     *         }
     *     ]
     * }
     * </pre>
     * </blockquote>
     * SqsTestPolicy:
     * <blockquote>
     * <pre>
     * {
     *     "Version": "2012-10-17",
     *     "Statement": [
     *         {
     *             "Effect": "Allow",
     *             "Action": "sqs:SendMessage",
     *             "Resource": "arn:aws:sqs:us-east-1:{ACCOUNT-NUMBER}:{MY-QUEUE-NAME}"
     *         }
     *     ]
     * }
     * </pre>
     * </blockquote>
     * */
    @Test
    public void messagePublisherTest() {
        help4DevsAwsSdkSqsService.sendMessage("{\"message\":\"testing\"}");
    }

}
