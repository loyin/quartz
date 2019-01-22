package com.quartz.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class QuartzIndex {

    @RequestMapping("/")
    public String login(){
        return "login";
    }

    @RequestMapping("/index")
    public String index(){
        return "quartz/index";
    }
}
