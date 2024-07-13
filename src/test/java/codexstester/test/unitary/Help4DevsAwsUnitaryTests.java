package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.dto.Help4DevsAwsS3ResponseDto;
import com.huntercodexs.demo.services.Help4DevsAwsS3Service;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.huntercodexs.demo.resources.Help4DevsFileHandlerService.byteFile;

public class Help4DevsAwsUnitaryTests extends Help4DevsBridgeTests {

    String filenameS3 = "80327789-737d-4b8a-9aa6-869af5b8706f.doc";
    String filepath = "src/main/resources";
    //String fileType = "conf";
    String fileType = "csv";
    //String fileType = "doc";
    //String fileType = "png";

    @Autowired
    Help4DevsAwsS3Service help4DevsAwsS3Service;

    @Test
    public void sendToS3Test() throws IOException {
        MultipartFile multipartFile = new MockMultipartFile(
                "file",
                "attach."+fileType,
                String.valueOf(MediaType.MULTIPART_FORM_DATA),
                byteFile(filepath+"/attach."+fileType));
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
