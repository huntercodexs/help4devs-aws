package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.service.Help4DevsAwsSdkSqsService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class Help4DevsAwsUnitaryTests extends Help4DevsBridgeTests {

    @Autowired
    Help4DevsAwsSdkSqsService help4DevsAwsSdkSqsService;

    @Test
    public void createSqsQueueTest() throws IOException {
        help4DevsAwsSdkSqsService.createQueue();
    }

    @Test
    public void listSqsQueueTest() throws IOException {
        help4DevsAwsSdkSqsService.listQueue();
    }

    @Test
    public void deleteSqsQueueTest() throws IOException {
        help4DevsAwsSdkSqsService.deleteQueue();
    }

    @Test
    public void sendMessageTest() {
        String url = "https://sqs.us-east-1.amazonaws.com/905418367021/sqs-help4devs-queue-test";
        String message = "test";
        help4DevsAwsSdkSqsService.sendMessage(url, message);
    }

    @Test
    public void readMessageTest() {
        String url = "https://sqs.us-east-1.amazonaws.com/905418367021/sqs-help4devs-queue-test";
        String message = "test";
        help4DevsAwsSdkSqsService.readMessage(url, message);
    }

    @Test
    public void cancelMessageTest() {
        String url = "https://sqs.us-east-1.amazonaws.com/905418367021/sqs-help4devs-queue-test";
        String message = "test";
        help4DevsAwsSdkSqsService.cancelMessage(url, message);
    }
}
