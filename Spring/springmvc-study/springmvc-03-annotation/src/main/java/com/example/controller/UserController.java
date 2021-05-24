package com.example.controller;

import com.example.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@RequestMapping(value="/user")
@Controller
public class UserController {
    private List<User> userList;
    {
        userList = new ArrayList<User>();
    }

    @RequestMapping(value="/showRegister")
    public String register() {
        return "register";
    }

    @RequestMapping(value="/addUser")
    public String login(
            @RequestParam("userName")String userName,
            @RequestParam("password")String password
    ) {
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        userList.add(user);
        return "login";
    }

    @RequestMapping("/showLogin")
    public String showLogin() {
        return "login";
    }

    @RequestMapping("/validateUser")
    public String login(@RequestParam("userName")String userName,
                        @RequestParam("password")String password, Model model) {
        for (User user: userList) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password))
                model.addAttribute("user", user);
                return "welcome";
        }
        return "login";
    }
}
