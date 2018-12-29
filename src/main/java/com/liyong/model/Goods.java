package com.liyong.model;

import java.util.Date;

import lombok.Data;

/**
 * 商品信息
 * @author LY
 */

@Data
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
	
	
}
