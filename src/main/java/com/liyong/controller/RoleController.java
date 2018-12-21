package com.liyong.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.liyong.model.Menu;
import com.liyong.model.PageBean;
import com.liyong.model.Result;
import com.liyong.model.Role;
import com.liyong.model.RoleMenu;
import com.liyong.service.MenuService;
import com.liyong.service.RoleService;
import com.liyong.until.ResponseUtil;
import com.liyong.until.StringUtil;
import com.mysql.fabric.xmlrpc.base.Array;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Resource
	private RoleService  roleService;
	
	@Resource
	private MenuService menuService;
	
	//查询菜单视图
		@RequestMapping("/manage")
		public String manage(Model model) throws Exception {
			model.addAttribute("pageTitle", "角色管理");
			model.addAttribute("title", "角色管理");
			return "pc/page/role/role_manage";
		}
	    //查询菜单视图数据	
		@RequestMapping("/list")
		public String list(@RequestParam(value = "page", required = false) String page,
				@RequestParam(value = "limit", required = false) String rows,
				@RequestParam(value = "q", required = false) String q, 
				@RequestParam(value = "date1", required = false) String date1, 
				@RequestParam(value = "date2", required = false) String date2, 
				@RequestParam(value = "type", required = false) String type, 
				HttpServletResponse response,
				HttpServletRequest request) throws Exception {
			PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("start", pageBean.getStart());
			map.put("size", pageBean.getPageSize());
			map.put("q", StringUtil.formatLike(q));
			map.put("date1", date1);
			map.put("date2", date2);
			map.put("type", type);
			
			List<Role> list = roleService.list(map);
			Integer total = roleService.getTotal(map);
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
			Role role = new Role();
			if(StringUtil.isNotEmpty(id)){
				role = roleService.findById(id);
			}
			model.addAttribute("role", role);
			model.addAttribute("btn_text", StringUtil.isNotEmpty(id) ? "修改":"添加");
			model.addAttribute("save_url", StringUtil.isNotEmpty(id) ? ("/role/addOrupdate?id="+id) :"/role/addOrupdate");			
			return "pc/page/role/role_add_or_update";
		}
		
		
		@RequestMapping("/ajaxRoleMenu")	
		public String ajaxRoleMenu(@RequestParam(value="roleId",required=false)String roleId, 
				HttpServletResponse response, HttpServletRequest request) throws Exception {

			List<RoleMenu> listRoleMenu  = new ArrayList<RoleMenu>();
			if(StringUtil.isNotEmpty(roleId)){
				Map<String,Object> findMap = new HashMap<String,Object>();
				findMap.put("roleId", roleId);
				findMap.put("activity", 1);
				listRoleMenu = roleService.getListRoleMenuByMap(findMap);
			}
			StringBuffer sub = new StringBuffer();
			if(listRoleMenu.size() > 0){
				for (RoleMenu roleMenu : listRoleMenu) {
					sub.append(roleMenu.getMenuId()).append(",");
				}
			}
			
			List<Map<String,Object>> resultListMap = new ArrayList<Map<String,Object>>();
			Map<String,Object> tempMap = new HashMap<>();
			
			List<Map<String,Object>> listTempMap = new ArrayList<Map<String,Object>>();
			Map<String,Object> tMp = new HashMap<>();
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("father","-1");//更换id不换ids继续查
			List<Menu> listMenu = menuService.getTreesByParentId(map);
			for (Menu me : listMenu) {
				
				tempMap = new HashMap<>();
				tempMap.put("title", me.getText());
				tempMap.put("value", me.getId());
				if(sub != null && sub.toString().indexOf(me.getId()) > -1){
					tempMap.put("checked",true);
				}
				listTempMap = new ArrayList<Map<String,Object>>();
				
				for (Menu menu : me.getChildren()) {
					tMp = new HashMap<>();
					tMp.put("title", menu.getText());
					tMp.put("value", menu.getId());
					List<Map<String,Object>>[] dataChrild = null;
					tMp.put("data", dataChrild);
					if(sub != null && sub.toString().indexOf(menu.getId()) > -1){
						tMp.put("checked",true);
					}
					listTempMap.add(tMp);
				}
				tempMap.put("data", listTempMap);
				resultListMap.add(tempMap);
			}	
			Gson gson = new Gson();
			ResponseUtil.write(response, gson.toJson(resultListMap));
			return null;
		}
		
		@RequestMapping("/addOrupdate")	
		public String update(Role role,
				HttpServletResponse response, HttpServletRequest request) throws Exception {			
			int resultTotal = roleService.addOrupdate(role);
			Result result = new Result();
			Gson gson = new Gson();
			if (resultTotal > 0) {
				result.setSuccess(true);
				result.setMsg("操作成功");
			} else {
				result.setSuccess(false);
				result.setMsg("操作失败");
			}
			ResponseUtil.write(response, gson.toJson(result));
			return null;
		}
		
		@RequestMapping("/delete")
		public String delete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)
				throws Exception {
			String[] idsStr = ids.split(",");
			Gson gson = new Gson();
			Result result = new Result();
			for (int i = 0; i < idsStr.length; i++) {
				roleService.delete(idsStr[i]);
			}
			result.setSuccess(true);
			ResponseUtil.write(response, gson.toJson(result));
			return null;
		}
}
