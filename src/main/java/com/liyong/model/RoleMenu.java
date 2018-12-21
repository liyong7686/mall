package com.liyong.model;

import java.util.Date;

//角色绑定菜单 中间表
//t_role_menu
public class RoleMenu {

	private String id ;
	private String roleId; 
	private String menuId;
	private Date createTime; //创建时间
	private int activity; //是否有效 
	
	private String checkedFlag; //ture;选中，false 没选中

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getCheckedFlag() {
		return checkedFlag;
	}

	public void setCheckedFlag(String checkedFlag) {
		this.checkedFlag = checkedFlag;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getActivity() {
		return activity;
	}

	public void setActivity(int activity) {
		this.activity = activity;
	}
	
	
	
	
}
