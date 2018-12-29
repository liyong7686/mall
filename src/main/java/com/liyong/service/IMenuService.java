package com.liyong.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liyong.model.Menu;
import com.liyong.until.CreateTablePrimaryId;
import com.liyong.until.MyUtil;

public interface IMenuService {

	public Menu findById(String id);
	public List<Menu> getTreesByFatherOrIds(Map<String, Object> map);
	public List<Menu> list(Map<String, Object> map);

	public Integer getTotal(Map<String, Object> map);
	
	public Integer addOrupdate(Menu menu);
	
	
	public List<Menu> getCheckTreesByParentId(String father, String treeIds) throws Exception;
	
	public List<Menu> getTreesByParentId(Map<String,Object> map) throws Exception;

}
