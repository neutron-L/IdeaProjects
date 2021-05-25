package com.example.controller;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class ModelTest {
    public String test(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        return "test";
    }
}
