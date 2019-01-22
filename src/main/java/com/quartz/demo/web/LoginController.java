package com.quartz.demo.web;

import com.quartz.demo.entity.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {


    @RequestMapping("login")
    public ModelAndView login(UserInfo userInfo) throws Exception {
        if("admin".equals(userInfo.getLoginName()) && "admin".equals(userInfo.getPassword())){
            return new ModelAndView("quartz/index");
        }
        throw new Exception("账号密码错误");
    }
}
