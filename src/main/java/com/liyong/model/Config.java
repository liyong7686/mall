package com.liyong.model;

import lombok.Data;

@Data
public class Config {
	
	private Integer id;
	private String domain_name;//网站的标题   蚂蚁科技-微信商城
	private String web_site;//网址 http://www.baiduc.com
	private String headStr;//head头里面的东西  万一修改head头   不用一个一个文件修改    高速模式  jq   和网站小图标
	//layui 版本
	private String layuiStr;
	
	
}
