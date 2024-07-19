package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.services.Help4DevsAwsSqsService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class Help4DevsAwsSqsUnitaryTests extends Help4DevsBridgeTests {

    @Autowired
    Help4DevsAwsSqsService help4DevsAwsSqsService;

    @Test
    public void messagePublisherTest() {
        help4DevsAwsSqsService.messagePublisher("test 00001");
        try {
            Thread.sleep(1000);
            help4DevsAwsSqsService.messagePublisher("test 00002");
            Thread.sleep(1000);
            help4DevsAwsSqsService.messagePublisher("test 00003");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
