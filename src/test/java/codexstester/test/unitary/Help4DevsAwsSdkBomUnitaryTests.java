package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.service.Help4DevsAwsSdkBomSqsService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class Help4DevsAwsSdkBomUnitaryTests extends Help4DevsBridgeTests {

    @Autowired
    Help4DevsAwsSdkBomSqsService help4DevsAwsSdkBomSqsService;

    @Test
    public void messageProducerTest() {
        help4DevsAwsSdkBomSqsService.createQueue();
        help4DevsAwsSdkBomSqsService.messageProducer("test");
    }

    @Test
    public void messageConsumerTest() {
        help4DevsAwsSdkBomSqsService.messageConsumer("test");
    }

    @Test
    public void messageEraserTest() {
        help4DevsAwsSdkBomSqsService.messageEraser("test");
        help4DevsAwsSdkBomSqsService.deleteQueue();
    }

}
