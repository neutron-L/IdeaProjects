package cn.edu.bupt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = {"cn.edu.bupt.OcrApp.mapper","cn.edu.bupt.OcrApp.dao"})
public class OcrAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(OcrAppApplication.class, args);
    }

}
