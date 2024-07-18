package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.service.Help4DevsAwsCoreSqsService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class Help4DevsAwsCoreSqsUnitaryTests extends Help4DevsBridgeTests {

    @Autowired
    Help4DevsAwsCoreSqsService help4DevsAwsCoreSqsService;

    @Test
    public void messagePublisherTest() {
        help4DevsAwsCoreSqsService.messagePublisher("test 00001");
        try {
            Thread.sleep(1000);
            help4DevsAwsCoreSqsService.messagePublisher("test 00002");
            Thread.sleep(1000);
            help4DevsAwsCoreSqsService.messagePublisher("test 00003");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
