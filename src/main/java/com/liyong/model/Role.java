package com.liyong.model;

import lombok.Data;

@Data
public class Role {

	private String id ; //主键id
	private String roleName ;// 角色名称
	private String roleRemark;  //角色描述
	
	private String menuIds; //角色所绑定的菜单id集合
	
	
}
