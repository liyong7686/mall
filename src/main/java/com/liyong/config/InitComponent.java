package com.liyong.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.liyong.model.Config;
import com.liyong.service.IConfigService;

@Component
public class InitComponent implements ServletContextListener,ApplicationContextAware{
	
	private static ApplicationContext applicationContext;
	
	
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application=sce.getServletContext();
		IConfigService configService=(IConfigService) applicationContext.getBean("configService");
		
		System.out.println("InitComponent.java .........初始到全局缓存中 把config  ...........start........ ");
		//把config  初始到全局缓存中
		Config config = configService.findById(1);
		System.out.println("------------" + config.getDomain_name() + "----------");
		//保存到 缓存中
		application.setAttribute("config", config);
		
	}
	
	
	
	public void contextDestroyed(ServletContextEvent sce) {
	}
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext=applicationContext;
	}

}
