package com.niuzj.controller;

import com.niuzj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getUser")
    public String getUser() {
        return userService.getUserList().toString();
    }

    @RequestMapping("/updateUsername")
    public String updateUsername(String username, Integer id) {
        return userService.updateUsername(username, id);
    }
}
