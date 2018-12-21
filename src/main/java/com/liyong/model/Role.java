package com.liyong.model;

import lombok.Data;

@Data
public class Role {

	private String id ; //主键id
	private String roleName ;// 角色名称
	private String roleRemark;  //角色描述
	
	private String menuIds; //角色所绑定的菜单id集合
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleRemark() {
		return roleRemark;
	}
	public void setRoleRemark(String roleRemark) {
		this.roleRemark = roleRemark;
	}
	public String getMenuIds() {
		return menuIds;
	}
	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}
	
	 
}
