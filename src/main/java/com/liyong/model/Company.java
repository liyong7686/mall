package com.liyong.model;

import java.util.Date;

import lombok.Data;

/**
 * 公司管理
 * @author LY
 *
 */
@Data
public class Company {

	private String id ;
	private String name;
	private String pid;
	private Date createTime;
	private String createBy;
	private int activity;
	
}
