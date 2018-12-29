package com.liyong.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.liyong.model.PageBean;
import com.liyong.model.ResultDTO;
import com.liyong.model.Menu;
import com.liyong.model.User;
import com.liyong.service.MenuService;
import com.liyong.service.UserService;
import com.liyong.until.CryptographyUtil;
import com.liyong.until.MyUtil;
import com.liyong.until.ResponseUtil;
import com.liyong.until.StringUtil;

@Controller
@RequestMapping("/menu")
public class MenuController {

	@Resource
	private MenuService menuService;
	@Resource
	private UserService  userService;
	
	//查询菜单视图
	@RequestMapping("/manage")
	public String manage(Model model) throws Exception {
		model.addAttribute("pageTitle", "菜单管理");
		model.addAttribute("title", "菜单管理");
		return "pc/page/menu/menu_manage";
	}
    //查询菜单视图数据	
	@RequestMapping("/list")
	public String list(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "limit", required = false) String rows,
			@RequestParam(value = "q", required = false) String q,
			HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("q", StringUtil.formatLike(q));
		
		List<Menu> list = menuService.list(map);
		Integer total = menuService.getTotal(map);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		
		map.clear();
		map.put("data", list);
		map.put("count", total);
		map.put("code", 0);
		map.put("msg", "");
		ResponseUtil.write(response, gson.toJson(map));
		return null;
	}
	
	@RequestMapping("/edit")
	public String edit(@RequestParam(value="id",required=false)String id
			,HttpServletResponse response
			,HttpServletRequest request
			,Model model) throws Exception {
		Menu menu = new Menu();
		Map<String,Object> map = new HashMap<String,Object>();
		if(id != null){
			map.put("id", id);
			List<Menu> menulist = menuService.getTreesByFatherOrIds(map);
			menu = menulist.get(0);
		}
		
		map = new HashMap<String,Object>();
		map.put("father","-1");
		List<Menu> parentMenulist = menuService.getTreesByFatherOrIds(map);
		
		model.addAttribute("parentMenulist",parentMenulist);
		model.addAttribute("menu", menu);
		model.addAttribute("btn_text", id != null ? "修改" : "增加");
		model.addAttribute("save_url",id != null ? ("/menu/addOrupdate?id="+id):"/menu/addOrupdate");
		return "pc/page/menu/menu_add_or_update";
	}
	@RequestMapping("/addOrupdate")
	public String update(Menu menu, HttpServletResponse response, HttpServletRequest request) throws Exception {
		int resultTotal = menuService.addOrupdate(menu);
		ResultDTO result = new ResultDTO();
		Gson gson = new Gson();
		if (resultTotal > 0) {
			result.setStatus(1);
			result.setMessage("修改成功");
		} else {
			result.setStatus(-1);
			result.setMessage("修改失败");

		}
		ResponseUtil.write(response, gson.toJson(result));
		return null;
	}
	
	/**
	 * 这个方法是设置权限用的 
	 * /tree/getCheckedTreeMenu?userId=11
	 * easyui哪个权限树菜单用。
	 * 
	 */
	@RequestMapping("/getCheckedTreeMenu")
	public String getCheckedTreeMenu(@RequestParam(value = "userId", required = false) String userId,
			HttpServletRequest requset, HttpServletResponse response) throws Exception {
		// 先找parent是-1的顶级菜单
		User user = userService.findById(Integer.parseInt(userId));
		
		String treeIds = user.getMenuIds();
		if(treeIds==null){
			treeIds="";//不能这null   会报错。 强制设置一个空str
		}
		
		List<Menu> list = menuService.getCheckTreesByParentId("-1", treeIds);
		Gson g = new Gson();
		ResponseUtil.write(response, g.toJson(list));
		return null;
	}
	
	
	
	
	
	/**
	 *  拿菜单
	 *  
	 *  
	 */
	@RequestMapping("/getMenu")
	public String getMenu(HttpServletResponse response)throws Exception {
		
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		currentUser = userService.findById(currentUser.getId());
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		String menuIds = currentUser.getMenuIds();
		if(menuIds==null){
			menuIds = "";
		}
		List<Integer> ids =MyUtil.Str_ids_To_ListInteger_ids(menuIds);  
		map.put("father", -1);
		map.put("ids", ids);
		List<Menu> treeList = menuService.getTreesByParentId(map);
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		ResponseUtil.write(response, gson.toJson(treeList));
		return null;
	}
	
}
