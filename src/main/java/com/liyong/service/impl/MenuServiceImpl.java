package com.liyong.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liyong.dao.MenuMapper;
import com.liyong.model.Menu;
import com.liyong.model.RoleMenu;
import com.liyong.model.User;
import com.liyong.service.IMenuService;
import com.liyong.until.CreateTablePrimaryId;
import com.liyong.until.MyUtil;



@Service
public class MenuServiceImpl implements IMenuService{
	
	@Autowired
	private MenuMapper menuMapper;

	public Menu findById(String id) {
		return menuMapper.findById(id);
	}

	public List<Menu> getTreesByFatherOrIds(Map<String, Object> map) {
		return menuMapper.getTreesByFatherOrIds(map);
	}
 
	public List<Menu> list(Map<String, Object> map) {
		return menuMapper.list(map);
	}

	public Integer getTotal(Map<String, Object> map) {
		return menuMapper.getTotal(map);
	}
	
	public Integer addOrupdate(Menu menu) {
		if(menu.getId() != null ){
			return menuMapper.update(menu);
		}else{
			menu.setId(CreateTablePrimaryId.tableId("t_tree"));
			return menuMapper.add(menu);
		}		
	}
	
	
	public List<Menu> getCheckTreesByParentId(String father, String treeIds) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("father", father+"");
		List<Menu> list = this.getTreesByFatherOrIds(map);//
		
		for (Menu tree : list) {
			// 如果 是复选框 可以在这里判断
			// tree.setChecked(true);
			if (MyUtil.existStrArr(tree.getId() + "", treeIds.split(","))) {// 判断id
																			// 有没有在ids之内，如果返回true
																			// 不在false
				tree.setChecked(true);
			}
			
			if ("open".equals(tree.getState())) {
				continue;
			} else {
				tree.setChildren(getCheckTreesByParentId(tree.getId(), treeIds));
			}
		}
		return list;
	}
	
	public List<Menu> getTreesByParentId(Map<String,Object> map) throws Exception {
		//String parentId,String ids  = map
		List<Menu> list = this.getTreesByFatherOrIds(map);
		for(Menu tree : list){
			//如果 是复选框  可以在这里判断   
			//tree.setChecked(true);
			if("open".equals(tree.getState())){
				continue;
			}else{
				map.put("father", tree.getId()+"");//更换id不换ids继续查
				tree.setChildren(getTreesByParentId(map));
			}
		}
		return list;
	}
	

	 
}
