package com.liyong.model;

public class Config {
	
	
	private Integer id;
	private String domain_name;//网站的标题   蚂蚁科技-微信商城
	private String web_site;//网址 http://www.baiduc.com
	private String headStr;//head头里面的东西  万一修改head头   不用一个一个文件修改    高速模式  jq   和网站小图标
	//layui 版本
	private String layuiStr;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDomain_name() {
		return domain_name;
	}
	public void setDomain_name(String domain_name) {
		this.domain_name = domain_name;
	}
	public String getWeb_site() {
		return web_site;
	}
	public void setWeb_site(String web_site) {
		this.web_site = web_site;
	}
	public String getHeadStr() {
		return headStr;
	}
	public void setHeadStr(String headStr) {
		this.headStr = headStr;
	}
	public String getLayuiStr() {
		return layuiStr;
	}
	public void setLayuiStr(String layuiStr) {
		this.layuiStr = layuiStr;
	}
	
	
	
	
}
