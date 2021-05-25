package com.ocr.service;

import com.ocr.pojo.User;

public interface UserService {
    //    add user
    int addUser(User user);


    //    query user
    User queryUserByName(String username);

    //    query user
    User queryUserById(int id);

}

