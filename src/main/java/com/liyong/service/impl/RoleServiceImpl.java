package com.liyong.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liyong.dao.RoleMapper;
import com.liyong.model.Role;
import com.liyong.model.RoleMenu;
import com.liyong.service.IRoleService;
import com.liyong.until.CreateTablePrimaryId;
import com.liyong.until.StringUtil;

@Service
public class RoleServiceImpl implements IRoleService{
	@Autowired
	private RoleMapper roleMapper;
	
	public Role findById(String id) {
		return roleMapper.findById(id);
	}
	public List<Role> list(Map<String, Object> map) {
		return roleMapper.list(map);
	}
	public Integer getTotal(Map<String, Object> map) {
		return roleMapper.getTotal(map);
	}
	public Integer addOrupdate(Role role) {
		
		if(role.getId() != null && !role.getId().equals("") && role.getId() != "null" ){
			addOrUpdateRoleMenu(role);
			return roleMapper.update(role);
		}else{
			role.setId(CreateTablePrimaryId.tableId("t_role"));
			addOrUpdateRoleMenu(role);
			return roleMapper.add(role);
		}
	}
	
	public boolean addOrUpdateRoleMenu(Role role){
		//不管是修改还是添加，都是需要对角色绑定菜单表进行操作的
		//1.先设置此角色下的所有菜单为无效状态。
		roleMapper.deleteRoleMenu(role);
		
		//2.新增角色绑定菜单表数据
		if(StringUtil.isNotEmpty(role.getMenuIds())){
			String[] menuids = role.getMenuIds().split(",");
			RoleMenu roleMenu = new RoleMenu();
			for (String strMenuId : menuids) {
				roleMenu = new RoleMenu();
				roleMenu.setId(CreateTablePrimaryId.tableId("t_role_menu"));
				roleMenu.setRoleId(role.getId());
				roleMenu.setMenuId(strMenuId);
				roleMenu.setCreateTime(new Date());
				roleMenu.setActivity(1);
				roleMapper.addRoleMenu(roleMenu);
			}
		}
		
		return true;
	}

	public Integer delete(String id) {
		return roleMapper.delete(id);
	}
	

	public List<RoleMenu> getListRoleMenuByMap(Map<String,Object> findMap){
		return roleMapper.getListRoleMenuByMap(findMap);
	}
	
}
