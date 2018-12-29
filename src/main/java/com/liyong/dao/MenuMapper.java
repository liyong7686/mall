package com.liyong.dao;


import java.util.List;
import java.util.Map;

import com.liyong.model.Menu;


public interface MenuMapper {
	
	public List<Menu> getTreesByFatherOrIds(Map<String,Object> map);
	
	
	public Menu findById(String id);
	public List<Menu> list(Map<String, Object> map) ;
	public Integer getTotal(Map<String, Object> map);
	public Integer update(Menu menu);
	public Integer add(Menu menu);
	
}