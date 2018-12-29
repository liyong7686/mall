package com.liyong.service;

import java.util.List;
import java.util.Map;

import com.liyong.model.User;

public interface IUserService {

	public Integer add(User user);

	public Integer update(User user) ;

	public List<User> list(Map<String, Object> map);

	public Integer getTotal(Map<String, Object> map);

	 
	public User findById(Integer id) ;
	public Integer delete(Integer id);

	public User findByNum(String num);
	
	public boolean saveOrUpdate(User user);
}
