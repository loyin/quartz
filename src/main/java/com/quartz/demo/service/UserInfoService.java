package com.quartz.demo.service;

import com.quartz.demo.dao.UserInfoRepository;
import com.quartz.demo.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    public UserInfo findByLoginNameAndPassword(UserInfo userInfo) {
        return userInfoRepository.findByLoginNameAndPassword(userInfo.getLoginName(), userInfo.getPassword());
    }
}
