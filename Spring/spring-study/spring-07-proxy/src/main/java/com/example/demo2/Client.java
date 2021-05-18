package com.example.demo2;

import com.example.pojo.Landlord;
import com.example.pojo.Proxy;
import com.example.service.UserService;
import com.example.service.UserServiceImpl;

public class Client {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        pih.setTarget(userService);
        UserService proxy = (UserService) pih.getProxy();

        proxy.delete();
        proxy.add();
        proxy.query();
        proxy.update();
    }
}
