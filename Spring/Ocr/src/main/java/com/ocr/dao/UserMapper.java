package com.ocr.dao;

import com.ocr.pojo.User;

public interface UserMapper {
//    add user
    int addUser(User user);


//    query user
    User queryUserByName(String username);

    //    query user
    User queryUserById(int id);
}
