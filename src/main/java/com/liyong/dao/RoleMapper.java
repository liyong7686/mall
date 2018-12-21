package com.liyong.dao;

import java.util.List;
import java.util.Map;

import com.liyong.model.Role;
import com.liyong.model.RoleMenu;

public interface RoleMapper {

	public Role findById(String id);
 
	public List<Role> list(Map<String, Object> map);
	public List<RoleMenu> getListRoleMenuByMap(Map<String,Object> findMap);

	public Integer getTotal(Map<String, Object> map);
	
	public Integer update(Role role);
	public Integer add(Role role);	
	public Integer delete(String id);
	
	public boolean deleteRoleMenu(Role role);
	
	public boolean addRoleMenu(RoleMenu roleMenu);
	
}
