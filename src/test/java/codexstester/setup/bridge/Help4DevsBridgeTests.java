package codexstester.setup.bridge;

import codexstester.engine.bridge.CodexsTesterCoreBridgeTests;
import com.huntercodexs.demo.Help4DevsAwsCoreLambdaFunctionApplication;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Help4DevsAwsCoreLambdaFunctionApplication.class) /*INSERT HERE THEM MAIN CLASS FROM PROJECT (EXAMPLE: ApplicationName.class)*/
@ContextConfiguration(classes = Help4DevsAwsCoreLambdaFunctionApplication.class)
public class Help4DevsBridgeTests extends CodexsTesterCoreBridgeTests {

    protected Help4DevsBridgeTests() {
        super("help4devs/");
    }

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

}