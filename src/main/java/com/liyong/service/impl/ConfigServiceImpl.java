package com.liyong.service.impl;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.liyong.dao.ConfigMapper;
import com.liyong.model.Config;
import com.liyong.service.IConfigService;

@Service("configService")
public class ConfigServiceImpl implements IConfigService {

	@Resource
	private ConfigMapper configMapper;
	
	public Integer update(Config config) {
		int i =  configMapper.update(config);
		config = configMapper.findById(1);
		//刷新缓存
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
        ServletContext servletContext = webApplicationContext.getServletContext(); 
        servletContext.setAttribute("config", config);
		return i ;
	}
	
	public Config findById(Integer id) {
		return configMapper.findById(id);
	}
}
