package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.service.Help4DevsAwsSdkSqsService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class Help4DevsAwsSdkSqsUnitaryTests extends Help4DevsBridgeTests {

    private static final String queueUrl = "https://sqs.us-east-1.amazonaws.com/905418367021/sqs-help4devs-queue-test";
    private static final String messageId = "1116a622-d9a3-47a4-a406-70fe5af6ab48";
    private static final String messageFilter = "";
    private static final String receiptHandle = "AQEBDs/uZrwqM103R4Yyn915MW4Q2xr7WCLaVoTChRoYQiMr7zogwsRBqXHpGkNH60t73ICz9Praa6sbMJL7nrH5MCix6h2pYZmy9zCNWJzMD5ibIbIVMR92TT+IXf1lAMUkKwbOPfaZXT0aBZdM2ICChGxovnWcMyFoifQGfQ/gsxiYQytOUA6+9ozVcbfScaRI6BIqakCAkSRzg5aPQb0OEdePzxmieabIoGfIgJy7uPslcVS4hZ94/lztJsFKrPlm9a1gH1kjREwTePL/N1a+/hxuzfV+dQgZIUZ1N8RoaPVBjZ338Aqq9jXKrx0h0tAQdba3/dejvPPyjA4B6CDyCn9pbDRJZ/SHaYOKrOLoKNzghgY+0EUJnSIGscEZmti9BLY3zQXiYWIA/tvK6m+OoA==\n";
    private static final String messageBody = "test sdk sqs java17 00001";

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
