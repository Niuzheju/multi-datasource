package com.niuzj.dao.user;

import com.niuzj.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    List<User> getUserList();

    int updateUsername(@Param("username") String username, @Param("id") Integer id);

}
