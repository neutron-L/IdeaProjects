package com.example.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
//说明类被spring接管了 注册到了容器中
@Component
public class User {
    private String name;

    public String getName() {
        return name;
    }

    @Value("darius")
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
