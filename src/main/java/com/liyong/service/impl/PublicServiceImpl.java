package com.liyong.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.liyong.model.Menu;
import com.liyong.model.User;
import com.liyong.service.IMenuService;
import com.liyong.service.IPublicService;
import com.liyong.service.IUserService;
import com.liyong.until.MyUtil;



@Service
public class PublicServiceImpl implements IPublicService {
	@Resource
	private IMenuService treeService;
	@Resource
	private IUserService userService;
 
	
	public void addLeftMenu(Model model) {
		
		model.addAttribute("leftPage", "pc/common/left_menu");
		
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		currentUser = userService.findById(currentUser.getId());
		
		System.out.println("当前用户：" + currentUser.getNum_() + "-------------- " + currentUser.getMenuIds());
		
		Map<String, Object> map = new HashMap<String, Object>();
		String menuIds = currentUser.getMenuIds();
		/**   17,171000,171005,171009,171011,171010   */
		if(menuIds==null){
			menuIds = "";
		}
		
		List<Integer> ids =MyUtil.Str_ids_To_ListInteger_ids(menuIds);  
		map.put("father", -1);
		map.put("ids", ids);
		
		System.out.println("当前用户：" + ids );

		if(ids.size()>0){
			
		}else{
			model.addAttribute("treeList", null);
		}
		try {
			List<Menu> treeList = getTreesByParentId(map);//   List<Integer> ids
			model.addAttribute("treeList", treeList);
			System.out.println("-------------------" + treeList.size() );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 拿菜单
	 */
	public List<Menu> getTreesByParentId(Map<String,Object> map) throws Exception {
		//String parentId,String ids  = map
		List<Menu> list = treeService.getTreesByFatherOrIds(map);
		//借阅
		
		
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
