package codexstester.test.unitary;

import codexstester.setup.bridge.Help4DevsBridgeTests;
import com.huntercodexs.demo.lambda.model.BookModel;
import com.huntercodexs.demo.lambda.repository.BookRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Help4DevsAwsSqsUnitaryTests extends Help4DevsBridgeTests {

    @Autowired
    BookRepository bookRepository;

    @Test
    public void createTest() {
        List<BookModel> result = bookRepository.create();

        for (BookModel bookModel : result) {
            System.out.println("Book: " + bookModel.getId());
            System.out.println(bookModel.getName());
            System.out.println(bookModel.getYear());
        }
    }

}
