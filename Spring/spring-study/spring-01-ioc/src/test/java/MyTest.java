import com.example.dao.UserDaoMysqlImpl;
import com.example.dao.UserDaoOracleImpl;
import com.example.pojo.User;
import com.example.service.UserService;
import com.example.service.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        UserServiceImpl userServiceImpl = context.getBean("userServiceImpl", UserServiceImpl.class);
        userServiceImpl.getUser();

        User user = context.getBean("user", User.class);
//        User user1 = context.getBean("user1", User.class);
//        User user2 = context.getBean("user2", User.class);
        User user3 = context.getBean("user3", User.class);
        User u = context.getBean("user3", User.class);
        System.out.println(u == user3);

        user.show();
//        user1.show();
//        user2.show();
        user3.show();

//        在配置文件加载时,所有容器已经存在了
    }
}
