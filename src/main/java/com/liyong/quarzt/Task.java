package com.liyong.quarzt;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.liyong.service.IWeixinCoreService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Task {
	
	@Resource
	private IWeixinCoreService weixinCoreService;
	
	//@Scheduled(cron="10,25,35,45,55 * * * * ?")
	public void weixin_token_task2() {
		log.info("模拟后台不定时获取token:");
		log.info(weixinCoreService.weixinToken(null));
	}
	
	//刷新微信Token
	//@Scheduled(cron="55 * * * * ?")
	public void AccessToke(){
		log.info("定时器每两小时刷新token");
		log.info(weixinCoreService.freshenWxToKen());
	}
	
}
