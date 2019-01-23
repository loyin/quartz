package com.quartz.demo.web;

import com.quartz.demo.entity.UserInfo;
import com.quartz.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("login")
    public @ResponseBody Boolean login(UserInfo userInfo, HttpServletRequest request){
        try{
            UserInfo user = userInfoService.findByLoginNameAndPassword(userInfo);
            request.getSession().setAttribute("userInfo", user);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @RequestMapping("loginOut")
    public void loginOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect("/");
    }
}
