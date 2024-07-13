package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.dto.Help4DevsAwsS3RequestDto;
import com.huntercodexs.demo.services.Help4DevsAwsS3Service;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static com.huntercodexs.demo.resource.Help4DevsFileHandlerService.byteFile;

public class Help4DevsAwsUnitaryTests extends Help4DevsBridgeTests {

    String filenameS3 = "70b1ca10-8553-433f-ad4c-693150f3d8b5.doc";
    String filepath = "src/main/resources";
    //String fileType = "conf";
    //String fileType = "csv";
    String fileType = "doc";
    //String fileType = "png";

    @Autowired
    Help4DevsAwsS3Service help4DevsAwsS3Service;

    @Test
    public void sendToS3Test() throws IOException {
        Help4DevsAwsS3RequestDto help4DevsAwsS3RequestDto = new Help4DevsAwsS3RequestDto();
        help4DevsAwsS3RequestDto.setFilename("attach."+fileType);
        help4DevsAwsS3RequestDto.setData(byteFile(filepath+"/attach."+fileType));
        System.out.println(help4DevsAwsS3Service.saveToS3(help4DevsAwsS3RequestDto));
    }

    @Test
    public void readFromS3Test() {
        String filename = "attach.png";
        byte[] bytes = Base64.getDecoder().decode(help4DevsAwsS3Service.readFromS3(filename));

        System.out.println("/*BINARY*/");
        System.out.println(new String(bytes, StandardCharsets.UTF_8));

        System.out.println("/*BASE64*/");
        System.out.println(new String(Base64.getEncoder().encode(bytes), StandardCharsets.UTF_8));
    }

}
