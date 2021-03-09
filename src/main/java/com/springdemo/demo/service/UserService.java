package com.springdemo.demo.service;

import com.springdemo.demo.mapper.UserMapper;
import com.springdemo.demo.model.User;
import com.springdemo.demo.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void logon(User user) {
        // check if user is already in SQL
        UserExample userExample1 = new UserExample();
        userExample1.createCriteria()
                .andAccountIdEqualTo(user.getAccountId());
        List<User> dbUsers = userMapper.selectByExample(userExample1);
        if (dbUsers.size() == 0) {
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        } else {
            User dbUser = dbUsers.get(0);
            User newUser = new User();
            newUser.setGmtModified(System.currentTimeMillis());
            newUser.setAvatarUrl(user.getAvatarUrl());
            newUser.setToken(user.getToken());
            newUser.setName(user.getName());
            UserExample userExample2 = new UserExample();
            userExample2.createCriteria()
                    .andIdEqualTo(dbUser.getId());
            userMapper.updateByExampleSelective(newUser, userExample2);
        }
    }
}
