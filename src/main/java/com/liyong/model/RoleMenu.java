package com.liyong.model;

import java.util.Date;

import lombok.Data;

//角色绑定菜单 中间表
//t_role_menu
@Data
public class RoleMenu {

	private String id ;
	private String roleId; 
	private String menuId;
	private Date createTime; //创建时间
	private int activity; //是否有效 
	
	private String checkedFlag; //ture;选中，false 没选中

	
}
