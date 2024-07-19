package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.service.Help4DevsAwsSdkSqsService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class Help4DevsAwsSdkSqsUnitaryTests extends Help4DevsBridgeTests {

    @Value("${cloud.aws.queue.name}")
    String queueName;

    @Autowired
    Help4DevsAwsSdkSqsService help4DevsAwsSdkSqsService;

    @Test
    public void messagePublisherBuilderTest() {
        help4DevsAwsSdkSqsService.messagePublisherBuilder("test aws sdk sqs java17 00010");
    }

    @Test
    public void messagePublisherStaticQueueTest() {
        help4DevsAwsSdkSqsService.messagePublisherStaticQueue("test aws sdk java17 00011");
    }

    @Test
    public void messagePublisherConvertTest() {
        help4DevsAwsSdkSqsService.messagePublisherConvert("test aws sdk java17 00012", queueName);
    }

}
