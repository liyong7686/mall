package com.liyong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liyong.dao.UserMapper;
import com.liyong.model.User;
import com.liyong.model.UserExample;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void saveOrUpdate(User user) {
        UserExample example = new UserExample();
        example.createCriteria().andOpenidEqualTo(user.getOpenid());
        List<User> users = userMapper.selectByExample(example);
        // 先查看是否有，如果有更新，没有创建
        if (users != null && users.size() != 0) {
            user.setGmtModified(System.currentTimeMillis());
            userMapper.updateByExampleSelective(user, example);
        } else {
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(System.currentTimeMillis());
            userMapper.insert(user);
        }
    }

    public User getByToken(String token) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andTokenEqualTo(token);
        List<User> users = userMapper.selectByExample(userExample);
        if (users != null && users.size() != 0) {
            return users.get(0);
        }
        return null;
    }
}
