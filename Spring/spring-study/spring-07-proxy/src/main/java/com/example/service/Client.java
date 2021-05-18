package com.example.service;

import com.example.pojo.Landlord;
import com.example.pojo.Proxy;

public class Client {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        UserServiceProxy proxy = new UserServiceProxy();
        proxy.setUserService(userService);
        proxy.query();
    }
}
