package com.ocr.controller;

import com.ocr.pojo.User;
import com.ocr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/user")
public class UserController {
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;


    @RequestMapping(value="/showLogin")
    public String showLogin() {
        return "login";
    }


    @RequestMapping("/login")
    public String validateUser(@RequestParam("username")String username,
                               @RequestParam("password")String password,Model model) {

        User user = userService.queryUserByName(username);

        if (user!=null && user.getPassword().equals(password))
            return "homepage";
        return "login";
    }

    @RequestMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @RequestMapping("/addUser")
    public String addUser(@RequestParam("username")String username,
                          @RequestParam("password")String password) {
        User user = new User();
        user.setUserName(username);
        user.setPassword(password);
        userService.addUser(user);
        return "login";
    }

}
