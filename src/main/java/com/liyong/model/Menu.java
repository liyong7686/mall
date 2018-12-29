package com.liyong.model;


import java.util.List;

import lombok.Data;

@Data
public class Menu {
	
	private String id ; 
	private String text ; //显示的名子
	private String father;//父节点
	private List<Menu> children;//子节点
	private Menu attributes;//其实也是note
	private boolean checked ; //easyui窗口   授权时会用到这个
	private String state ;//open就是代码到了叶子节点。  close就是支干  下面有可能有节点 
	private String url;//对应的页面  /station/pc/manage
	private String iconCls;//对应的图标
	private String permissions;//对应的shiro权限    job:change
	/**
	 * 这个这是记录 id的 因为在manage页面中，
	 * 有这样一个东西。$("#user").addClass("layui-this");  
	 * 选择哪个选项为选中  这是根据id来的。
	 */
	private String dd_id;
	

}
