//package cn.edu.bupt.OcrApp.utils;
//
//import cn.edu.bupt.common.utils.QiniuCloudUtil;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.File;
//import java.util.Optional;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class QiNiuUtilsTest {
//    @Test
//    public void qiNiuTest(){
//        QiniuCloudUtil qiniuCloudUtil = new QiniuCloudUtil();
//        String property = System.getProperty("user.dir");
//        System.out.println(property);
//        File file = new File("C:\\Users\\49522\\Pictures\\Camera Roll\\timg.jpg");
//        Optional<String> upload = qiniuCloudUtil.upload(file,"timg.jpg");
//        upload.ifPresent(System.out::println);
//    }
//}
