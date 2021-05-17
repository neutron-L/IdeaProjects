package com.example.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//等价于 <bean id="user" class="com.example.dao.User" />
@Component(value = "u")
@Scope("prototype")
public class User {
    //相当于<property name="name" value=".." />
//    @Value("hehehe")
    public String name;

    @Value("hehehe")
    public void setName(String name) {
        this.name=name;
    }
}
