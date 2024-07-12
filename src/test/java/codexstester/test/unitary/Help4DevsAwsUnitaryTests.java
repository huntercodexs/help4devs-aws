package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.dto.Help4DevsAwsS3ResponseDto;
import com.huntercodexs.demo.services.Help4DevsAwsS3Service;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static codexstester.setup.datasource.MediaTests.ioFile;

public class Help4DevsAwsUnitaryTests extends Help4DevsBridgeTests {

    String filenameS3 = "a334c4d3-bca1-4d26-8335-198d54ac0f3b.txt";
    String filepath = "src/test/java/codexstester/setup/datasource";

    @Autowired
    Help4DevsAwsS3Service help4DevsAwsS3Service;

    @Test
    public void sendToS3Test() throws IOException {
        byte[] fileContent = ioFile(filepath+"/selfie.txt").getBytes(StandardCharsets.UTF_8);
        MultipartFile multipartFile = new MockMultipartFile(
                "file",
                "selfie.txt",
                "text/plain",
                fileContent);
        Help4DevsAwsS3ResponseDto result = help4DevsAwsS3Service.uploadFile(multipartFile);
        System.out.println(result.getFilename());
        System.out.println(result.getMessage());
    }

    @Test
    public void readFromS3Test() {
        byte[] result = help4DevsAwsS3Service.downloadFile(filenameS3);
        String file = new String(result);
        System.out.println(file);
    }

    @Test
    public void deleteFromS3Test() {
        Help4DevsAwsS3ResponseDto result = help4DevsAwsS3Service.deleteFile(filenameS3);
        System.out.println(result.getFilename());
        System.out.println(result.getMessage());
    }

}
