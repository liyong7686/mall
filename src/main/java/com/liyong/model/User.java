package com.liyong.model;

import java.util.Date;

import lombok.Data;

@Data
public class User {
	
	private Integer id;
	private String num_;//读者号
	private String trueName;//真实姓名 
	private String phone;//电话 
	private String sex_;//性别
	private String menuIds;
	private Date createDateTime;
	private String password;//密码
	private String remark;//备注
	private Integer type;   // 1、平台管理人员、 2、 商户管理人员
	
	private String token;
	private String openId;
	
	
}
