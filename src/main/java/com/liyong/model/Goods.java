package com.liyong.model;

import java.util.Date;

/**
 * 商品信息
 * @author LY
 */
public class Goods {

	private String id ;       //商品id
	private String name ;     //商品名称
	private String describes;  //描述说明
	private String imgPath;   //商品图片
	private int number;    //商品库存
	private String commodity; //0，未审核，1，审核通过，-1 ，审核不通过
	private String status ;   //0，未上架，1, 已上架 
	private Date startTime;   //上架开始时间
	private Date endTime ;    //上架结束时间
	
	private int activity;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescribes() {
		return describes;
	}
	public void setDescribes(String describes) {
		this.describes = describes;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getCommodity() {
		return commodity;
	}
	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getActivity() {
		return activity;
	}
	public void setActivity(int activity) {
		this.activity = activity;
	}
	
	
	
}
