package com.quartz.demo.base.configuration;

import com.quartz.demo.entity.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

@Controller
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("----------------进入拦截器----------------");
        String basePath = request.getContextPath();
        String path = request.getRequestURI();
        //是否进行登陆拦截
        if (doLoginInterceptor(path, basePath)) {
            return true;
        }
        //如果登录了，会把用户信息存进session
        HttpSession session = request.getSession();
        UserInfo users = (UserInfo) session.getAttribute("userInfo");
        if (users == null) {
            String requestType = request.getHeader("X-Requested-With");
            if (requestType != null && requestType.equals("XMLHttpRequest")) {
                response.setHeader("sessionstatus", "timeout");
                response.getWriter().print("LoginTimeout");
                return false;
            } else {
                log.info("尚未登录，跳转到登录界面" + request.getContextPath());
                response.sendRedirect("/");
            }
            return false;
        }
        return true;
    }

    /**
     * 是否进行登陆过滤
     */
    private boolean doLoginInterceptor(String path, String basePath) {
        path = path.substring(basePath.length());
        Set<String> notLoginPaths = new HashSet<>();
        //设置不进行登录拦截的路径：登录注册和验证码
        notLoginPaths.add("/");
        notLoginPaths.add("/login");
        notLoginPaths.add("/register");
        notLoginPaths.add("/static/**");
        if (notLoginPaths.contains(path)) {
            return true;
        }
        return false;
    }

}