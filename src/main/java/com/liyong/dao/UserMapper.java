package com.liyong.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.liyong.model.User;
import com.liyong.model.UserExample;

public interface UserMapper {
public Integer add(User user);
	
	public Integer update(User user);
	
	public List<User> list(Map<String, Object> map);
	
	public Integer getTotal(Map<String, Object> map);
	
	public User findByNum(String num);
	
	public User findById(Integer id);
	
	public Integer delete(Integer id);
}
