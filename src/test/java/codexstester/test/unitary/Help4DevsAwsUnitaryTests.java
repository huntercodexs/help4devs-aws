package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.services.Help4DevsAwsS3Service;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class Help4DevsAwsUnitaryTests extends Help4DevsBridgeTests {

    public static String path = "src/test/java/codexstester/setup/datasource";

    @Autowired
    Help4DevsAwsS3Service help4DevsAwsS3Service;

    @Test
    public void sendToS3Test() throws IOException {

    }

    @Test
    public void readFromS3Test() {

    }

    @Test
    public void deleteFromS3Test() {

    }

}
