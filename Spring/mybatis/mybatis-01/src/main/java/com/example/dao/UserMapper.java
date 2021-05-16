package com.example.dao;

import com.example.pojo.User;

import java.util.List;

public interface UserMapper {
    List<User> getUserList();

    User getUserById(int id);

    //insert a user
    int addUser(User user);

    int updateUser(User user);

    int deleteUser(int id);

}
