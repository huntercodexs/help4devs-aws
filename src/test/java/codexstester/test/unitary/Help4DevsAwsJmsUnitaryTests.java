package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.service.Help4DevsAwsJmsSqsService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;

public class Help4DevsAwsJmsUnitaryTests extends Help4DevsBridgeTests {

    @Autowired
    Help4DevsAwsJmsSqsService help4DevsAwsJmsSqsService;

    @Test
    public void messageProducerStandardTest() throws JMSException, InterruptedException {
        help4DevsAwsJmsSqsService.messageProducerStandard("test");
    }

    @Test
    public void messageConsumerStandardTest() throws JMSException, InterruptedException {
        help4DevsAwsJmsSqsService.messageConsumerStandard();
    }

    @Test
    public void messageProducerFifoTest() throws JMSException, InterruptedException {
        help4DevsAwsJmsSqsService.messageProducerFifo("test");
    }

    @Test
    public void messageConsumerFifoTest() throws JMSException, InterruptedException {
        help4DevsAwsJmsSqsService.messageConsumerFifo();
    }

}
