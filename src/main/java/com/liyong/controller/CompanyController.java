package com.liyong.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.liyong.model.Company;
import com.liyong.model.Menu;
import com.liyong.model.PageBean;
import com.liyong.model.Result;
import com.liyong.model.User;
import com.liyong.service.CompanyService;
import com.liyong.service.MenuService;
import com.liyong.until.ResponseUtil;
import com.liyong.until.StringUtil;

@Controller
@RequestMapping("/company")
public class CompanyController {
	  
	@Resource
	private MenuService menuService;
	@Resource
	private CompanyService companyService;

	    //查询菜单视图
		@RequestMapping("/manage")
		public String manage(Model model) throws Exception {
			model.addAttribute("pagename", "菜单管理");
			model.addAttribute("name", "菜单管理");
			return "pc/page/company/company_manage";
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
			Result result = new Result();
			Gson gson = new Gson();
			if (resultTotal > 0) {
				result.setSuccess(true);
				result.setMsg("修改成功");
			} else {
				result.setSuccess(false);
				result.setMsg("修改失败");
			}
			ResponseUtil.write(response, gson.toJson(result));
			return null;
		}
		
		
		@RequestMapping("/treeData")
		public String treeData(@RequestParam(value = "userId", required = false) String userId,
				HttpServletRequest requset, HttpServletResponse response) throws Exception {
			// 先找parent是-1的顶级菜单
			
			List<Map<String,Object>> treeMap = new ArrayList<Map<String,Object>>();
			Company company = new Company();
			List<Company> listCompany = this.companyService.findCompanyList(company);
			
			Map<String,Object> tMap = new HashMap<String,Object>();
			for (Company comp : listCompany) {
				tMap = new HashMap<String,Object>();
				tMap.put("id", comp.getId());
				tMap.put("pid", comp.getPid());
				tMap.put("name",comp.getName());
				treeMap.add(tMap);
			}

			Gson g = new Gson();
			
			//System.out.println("jsonDate:");
			//System.out.println(g.toJson(treeMap));
			
			ResponseUtil.write(response, g.toJson(treeMap));
			return null;
		}
		
}
