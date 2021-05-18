package com.example.config;

import com.example.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//也会被spring接管 注册到容器中 本来就是一个component
@Configuration
public class MyConfig {
    //注册一个bean 相当于我们之前写的一个bean标签
    //方法名字相当于id
    //返回值相当于class
    @Bean
    public User getUser() {
        return new User();
    }
}
