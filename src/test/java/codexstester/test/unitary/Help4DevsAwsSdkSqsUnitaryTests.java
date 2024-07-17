package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.service.Help4DevsAwsSdkSqsService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class Help4DevsAwsSdkSqsUnitaryTests extends Help4DevsBridgeTests {

    private static final String queueUrl = "{URL}";
    private static final String messageId = "1116a622-d9a3-47a4-a406-70fe5af6ab48";
    private static final String messageFilter = "";
    private static final String receiptHandle = "{RECEIPT}";
    private static final String messageBody = "test sdk sqs java17 002";

    @Value("${cloud.aws.queue.name}")
    String queueName;

    @Autowired
    Help4DevsAwsSdkSqsService help4DevsAwsSdkSqsService;

    @Test
    public void queueCreatorTest() {
        help4DevsAwsSdkSqsService.queueCreator(queueName);
    }

    @Test
    public void messageProducerTest() {
        help4DevsAwsSdkSqsService.messageProducer(messageBody, queueUrl);
    }

    @Test
    public void messageConsumer() {
        help4DevsAwsSdkSqsService.messageConsumer(messageFilter, queueUrl);
    }

    @Test
    public void messageEraserTest() {
        help4DevsAwsSdkSqsService.messageEraser(receiptHandle, queueUrl);
    }

    @Test
    public void queueEraserTest() {
        help4DevsAwsSdkSqsService.queueEraser(queueName);
    }

}
