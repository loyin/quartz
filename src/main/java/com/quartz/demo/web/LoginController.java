package com.quartz.demo.web;

import com.quartz.demo.entity.UserInfo;
import com.quartz.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    private UserInfoService userInfoService;

    @ResponseBody
    @RequestMapping("login")
    public Boolean login(UserInfo userInfo, HttpServletRequest request){
        try{
            UserInfo user = userInfoService.findByLoginNameAndPassword(userInfo);
            request.getSession().setAttribute("userInfo", user);
            return true;
        } catch (Exception e){
            return false;
        }

    }
}
