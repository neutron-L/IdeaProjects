import com.example.pojo.Books;
import com.example.service.BookService;
import com.example.service.BookServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void test() {
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");

        BookService bookService= (BookService) context.getBean("bookServiceImpl");
//        System.out.println(bookService.queryBookById(1));
        for (Books books: bookService.queryAllBook())
            System.out.println(books);

    }
}