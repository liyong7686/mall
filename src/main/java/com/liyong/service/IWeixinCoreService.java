package com.liyong.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IWeixinCoreService {

   public String processRequest(HttpServletRequest request, HttpServletResponse response) ;

	//每个公司一个微信号
	public String weixinToken(String companyId) ;
	//刷新微信token
	public String freshenWxToKen() ;
}
