package org.example.config;

import org.example.pojo.Hello;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//配置类 也会被spring容器托管
@Configuration
public class MyConfig {

    //注册一个bean 相当于之前写的一个bean标签
    //这个方法的名字 == bean标签中的id
    //方法的返回者 == bean标签中的class属性
    @Bean
    public Hello getHello() {
        return new Hello();
    }
}
