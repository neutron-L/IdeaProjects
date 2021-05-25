package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControllerTest2 {
    @RequestMapping("/t/{name}/{habit}")
    public String test(@PathVariable String name, @PathVariable String habit, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("habit", habit);
        return "test";
    }

//    @RequestMapping("/t")
//    public String test2(@RequestParam("name") String name, @RequestParam("habit")String habit, Model model) {
//        model.addAttribute("name", name);
//        model.addAttribute("habit", habit);
//        return "test";
//    }
}
