package com.project.community.service;

import com.project.community.dao.UserMapper;
import com.project.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findUserById (int id){
        return userMapper.selectById(id);
    }
}
