package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.services.Help4DevsAwsCoreSqsService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class Help4DevsAwsCoreSqsUnitaryTests extends Help4DevsBridgeTests {

    @Autowired
    Help4DevsAwsCoreSqsService help4DevsAwsCoreSqsService;

    @Test
    public void messagePublisherTest() {
        help4DevsAwsCoreSqsService.messagePublisher(
                "https://localhost.localstack.cloud:4566/000000000000/sqs-help4devs-queue-test",
                "test sqs 00004");
    }

}
