package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.service.Help4DevsAwsSqsService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class Help4DevsAwsCoreSqsUnitaryTests extends Help4DevsBridgeTests {

    @Autowired
    Help4DevsAwsSqsService help4DevsAwsSqsService;

    @Test
    public void messagePublisherTest() {
        help4DevsAwsSqsService.messagePublisher("test aws sqs 3");
    }

}
