package com.example.service;

import com.example.dao.UserDao;
import com.example.dao.UserDaoImpl;
import com.example.dao.UserDaoMysqlImpl;
import com.example.dao.UserDaoOracleImpl;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void getUser() {
        userDao.getUser();
    }
}
