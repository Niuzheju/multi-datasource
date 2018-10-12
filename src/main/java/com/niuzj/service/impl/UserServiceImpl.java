package com.niuzj.service.impl;

import com.niuzj.dao.user.UserDao;
import com.niuzj.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl {

    @Autowired
    private UserDao userDao;

    List<User> getUserList(){
        return userDao.getUserList();
    }
}
