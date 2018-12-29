package com.liyong.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.liyong.service.PublicService;
import com.liyong.until.MyUtil;

@Controller
public class IndexController {
	
	@Resource
	private PublicService publicService;

	@RequestMapping("/")
	public String index(HttpServletResponse response,HttpServletRequest request )throws Exception{		

		return "pc/page/login/login";
	}
	
	
	/**
	 * 后台主页
	 */
	@RequestMapping("/admin/main")
	public String admin_main(HttpServletResponse  res,HttpServletRequest req,Model model) throws Exception {
		ModelAndView mav = new ModelAndView();
		publicService.addLeftMenu(model);
		
		System.out.println("请求的地址信息：" + MyUtil.getRemoteAddress(req));
		
		String UserAgent = req.getHeader("User-Agent");
		String returnUrl ="";
		if(MyUtil.checkUserAgent(UserAgent)){
			returnUrl = "pc/main";
		}else{
			returnUrl = "/admin/common/s_mode";
		}
		model.addAttribute("abcd", "12312321");
		System.out.println("登陆后，跳转到后台主页保存数值------------main");
		return returnUrl;
	}
	
}
