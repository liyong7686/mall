package com.liyong.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.liyong.model.Role;
import com.liyong.model.RoleMenu;
import com.liyong.until.CreateTablePrimaryId;
import com.liyong.until.StringUtil;

public interface IRoleService {

	public Role findById(String id) ;
	public List<Role> list(Map<String, Object> map) ;
	public Integer getTotal(Map<String, Object> map);
	public Integer addOrupdate(Role role) ;
	
	public boolean addOrUpdateRoleMenu(Role role);

	public Integer delete(String id) ;
	

	public List<RoleMenu> getListRoleMenuByMap(Map<String,Object> findMap);
}
