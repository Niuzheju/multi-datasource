package com.niuzj.service.impl;

import com.niuzj.dao.user.UserDao;
import com.niuzj.model.DataSourceManage;
import com.niuzj.model.DataSourceName;
import com.niuzj.model.DatasourceTypeEnum;
import com.niuzj.model.User;
import com.niuzj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getUserList(){
        return userDao.getUserList();
    }

    @Override
    @DataSourceName(name = DatasourceTypeEnum.WRITE)
    public String updateUsername(String username, Integer id) {
        try {
            int i = userDao.updateUsername(username, id);
            if (i > 0){
                return "更新成功";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "更新失败";
    }
}
