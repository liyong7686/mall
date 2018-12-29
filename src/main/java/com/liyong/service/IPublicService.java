package com.liyong.service;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.liyong.model.Menu;

public interface IPublicService {

   public void addLeftMenu(Model model) ;
	
	/**
	 * 拿菜单
	 */
	public List<Menu> getTreesByParentId(Map<String,Object> map) throws Exception ;
}
