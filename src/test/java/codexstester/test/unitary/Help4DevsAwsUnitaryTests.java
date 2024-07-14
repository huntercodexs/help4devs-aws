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
        help4DevsAwsSdkSqsService.create();
    }

    @Test
    public void listSqsQueueTest() throws IOException {
        help4DevsAwsSdkSqsService.list();
    }

    @Test
    public void deleteSqsQueueTest() throws IOException {
        help4DevsAwsSdkSqsService.delete();
    }

}
