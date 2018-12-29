package com.liyong.controller;

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
import com.liyong.model.Goods;
import com.liyong.model.PageBean;
import com.liyong.model.ResultDTO;
import com.liyong.service.GoodsService;
import com.liyong.until.ResponseUtil;
import com.liyong.until.StringUtil;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	
	@Resource
	private GoodsService goodsService;

	//查询菜单视图
		@RequestMapping("/manage")
		public String manage(Model model) throws Exception {
			model.addAttribute("pageTitle", "商品管理");
			model.addAttribute("title", "商品管理");
			return "pc/page/goods/goods_manage";
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
			map.put("activity",1);
			
			System.out.println("查询的条件是：" + map.toString());
			List<Goods> list = goodsService.list(map);
			System.out.println("查询的结果是：" + list.size());
			Integer total = goodsService.getTotal(map);
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
			Goods goods = new Goods();
			if(id != null){
				goods = goodsService.findById(id);
			}
			model.addAttribute("goods", goods);
			model.addAttribute("btn_text", id != null ? "修改" : "增加");
			model.addAttribute("save_url",id != null ? ("/goods/addOrupdate?id="+id):"/goods/addOrupdate");
			return "pc/page/goods/goods_add_or_update";
		}
		@RequestMapping("/addOrupdate")
		public String update(Goods goods, HttpServletResponse response, HttpServletRequest request) throws Exception {
			int resultTotal = goodsService.addOrupdate(goods);
			ResultDTO result = new ResultDTO();
			Gson gson = new Gson();
			if (resultTotal > 0) {
				result.setStatus(1);
				result.setMessage("操作成功");
			} else {
				result.setStatus(-1);
				result.setMessage("操作失败");
			}
			ResponseUtil.write(response, gson.toJson(result));
			return null;
		}
		
		@RequestMapping("/delete")
		public String delete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)
				throws Exception {
			String[] idsStr = ids.split(",");
			Gson gson = new Gson();
			ResultDTO result = new ResultDTO();
			for (int i = 0; i < idsStr.length; i++) {
				goodsService.delete(idsStr[i]);
			}
			result.setStatus(1);
			ResponseUtil.write(response, gson.toJson(result));
			return null;
		}
}
