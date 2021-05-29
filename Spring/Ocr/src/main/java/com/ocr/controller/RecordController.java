package com.ocr.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value="/record")
public class RecordController {
    @RequestMapping("/upload")
    public String upload(@RequestParam("photo") MultipartFile photo, Model model) {

//      get photo

//        store in mysql
//
//        send to url

//        set the result
        model.addAttribute("msg", photo.getOriginalFilename());

        return "homepage";
    }
}
