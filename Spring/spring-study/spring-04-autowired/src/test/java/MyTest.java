import com.example.pojo.Human;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        Human human = context.getBean("human", Human.class);
        human.getDog().shout();
        human.getCat().shout();
    }
}
