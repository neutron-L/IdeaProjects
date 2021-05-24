package com.example.controller;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//注意：这里我们先导入Controller接口
public class HelloController implements Controller {

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //ModelAndView 模型和视图
        ModelAndView mv = new ModelAndView();

        //封装对象，放在ModelAndView中。Model
        mv.addObject("msg","HelloSpringMVC_02 现在是在HelloContrller类中哦!");
        //封装要跳转的视图，放在ModelAndView中
        mv.setViewName("hello"); // 最后拼接成为 /WEB-INF/jsp/hello123.jsp  这是根据springmvc-servlet.xml中的前缀后缀拼接而成
        return mv;
    }

}
