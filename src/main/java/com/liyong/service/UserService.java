package com.liyong.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liyong.dao.UserMapper;
import com.liyong.model.User;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public Integer add(User user) {
		return userMapper.add(user);
	}

	public Integer update(User user) {
		return userMapper.update(user);
	}

	public List<User> list(Map<String, Object> map) {
		return userMapper.list(map);
	}

	public Integer getTotal(Map<String, Object> map) {
		return userMapper.getTotal(map);
	}

	 
	public User findById(Integer id) {
		return userMapper.findById(id);
	}

	public Integer delete(Integer id) {
		return userMapper.delete(id);
	}

	public User findByNum(String num) {
		return userMapper.findByNum(num);
	}
	
<<<<<<< HEAD
	public boolean saveOrUpdate(User user){
		if(user.getId() != null){
			userMapper.add(user);
		}else{
			userMapper.update(user);
		}
		return true;
	}
	
=======
>>>>>>> branch 'master' of https://github.com/liyong7686/mall.git
}
