package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.service.Help4DevsAwsSqsListener;
import com.huntercodexs.demo.service.Help4DevsAwsSqsService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class Help4DevsAwsCoreSqsUnitaryTests extends Help4DevsBridgeTests {

    @Autowired
    Help4DevsAwsSqsService help4DevsAwsSqsService;

    @Autowired
    Help4DevsAwsSqsListener help4DevsAwsSqsListener;

    @Test
    public void messagePublisherTest() {
        help4DevsAwsSqsService.messagePublisher("test aws localhost.stack 7");
    }

    @Test
    public void messageConsumerTest() {
        System.out.println("Calling Listener");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Finished Listener");
    }

}
