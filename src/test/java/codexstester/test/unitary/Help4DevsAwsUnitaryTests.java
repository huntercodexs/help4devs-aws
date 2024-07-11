package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.dto.Help4DevsAwsS3RequestDto;
import com.huntercodexs.demo.services.Help4DevsAwsS3Service;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static codexstester.setup.datasource.MediaTests.ioFile;

public class Help4DevsAwsUnitaryTests extends Help4DevsBridgeTests {

    public static String path = "src/test/java/codexstester/setup/datasource";

    @Autowired
    Help4DevsAwsS3Service help4DevsAwsS3Service;

    @Test
    public void sendToS3Test() throws IOException {
        Help4DevsAwsS3RequestDto help4DevsAwsS3RequestDto = new Help4DevsAwsS3RequestDto();
        help4DevsAwsS3RequestDto.setFilename("");
        help4DevsAwsS3RequestDto.setData(ioFile(path+"/selfie.txt").getBytes(StandardCharsets.UTF_8));
        System.out.println(help4DevsAwsS3Service.saveToS3(help4DevsAwsS3RequestDto));
    }

    @Test
    public void readFromS3Test() {
        String guid = "3df0fb4e-3947-49b0-ba99-2df25c0b9feb";
        byte[] bytes = Base64.getDecoder().decode(help4DevsAwsS3Service.readFromS3(guid));

        System.out.println("/*BASE 64*/");
        System.out.println(new String(bytes, StandardCharsets.UTF_8));

        System.out.println("/*BINARY*/");
        System.out.println(new String(Base64.getDecoder().decode(bytes), StandardCharsets.UTF_8));
    }

}
