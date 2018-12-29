package com.liyong.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.liyong.model.PageBean;
import com.liyong.model.ResultDTO;
import com.liyong.model.User;
import com.liyong.service.UserService;
import com.liyong.until.CryptographyUtil;
import com.liyong.until.ResponseUtil;
import com.liyong.until.StringUtil;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService  userService;
	
	/**
	 * /user/login
	 * 电脑登陆
	 */
	@RequestMapping("/login")
	public String login(User user,HttpServletResponse response,HttpServletRequest request
			,RedirectAttributes attr)throws Exception{
		ResultDTO result = new  ResultDTO();
		Gson gson = new Gson();
		Subject subject=SecurityUtils.getSubject();
		
		SecurityUtils.getSubject().getSession().setAttribute("login_type", "user_login");
		
		//UsernamePasswordToken token=new UsernamePasswordToken(user.getNum_(), CryptographyUtil.md5(user.getPassword(), "chenhao"));
		
		try{
			//subject.login(token); // 登录验证
			//如果登陆成功 就不会报错  报错就是登陆失败了
			user = userService.findByNum(user.getNum_());
			SecurityUtils.getSubject().getSession().setAttribute("currentUser", user); //把当前用户信息存到session中
			
			result.setStatus(1);
			result.setMessage("登陆成功");
			ResponseUtil.write(response, gson.toJson(result));
			return null;
		}catch(Exception e){
			e.printStackTrace();
			result.setStatus(-1);
			result.setMessage("帐号或密码错误");
			ResponseUtil.write(response, gson.toJson(result));
			return null;
		}
	}
	
	@RequestMapping("/logout")
	public String logout()throws Exception{
		SecurityUtils.getSubject().logout(); //shiro的退出
		return "redirect:/";
	}
	
	
	@RequestMapping("/add")
	public String add(User user, HttpServletResponse response, HttpServletRequest request) throws Exception {
		user.setPassword(CryptographyUtil.md5(user.getPassword(), "chenhao"));
		user.setCreateDateTime(new Date());
		int resultTotal = userService.add(user);
		ResultDTO result = new ResultDTO();
		Gson gson = new Gson();
		if (resultTotal > 0) {
			result.setStatus(1);
			result.setMessage("添加成功");
		} else {
			result.setStatus(-1);
			result.setMessage("添加失败");
		}
		ResponseUtil.write(response, gson.toJson(result));
		return null;
	}
	
	
	@RequestMapping("/edit")
	public String edit(@RequestParam(value="id",required=false)String id
			,HttpServletResponse response
			,HttpServletRequest request
			,Model model) throws Exception {
		
		User user = userService.findById(Integer.parseInt(id));
		model.addAttribute("user", user);
		model.addAttribute("btn_text", "修改");
		model.addAttribute("save_url", "/user/update?id="+id);
		model.addAttribute("user", user);
		
		return "pc/page/user/user_add_or_update";
	}
	/**
	 * /admin/user/update
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/update")
	public String update(User user, HttpServletResponse response, HttpServletRequest request) throws Exception {
		if(StringUtil.isNotEmpty(user.getPassword())){
			user.setPassword(CryptographyUtil.md5(user.getPassword(),"chenhao"));
		}		
		int resultTotal = userService.update(user);
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
	 * 修改 密码 在不退出登陆的情况下。可以多次修改密码。
	 */
	@RequestMapping("/modifyPassword")
	public String modifyPassword(String newPassword, HttpServletResponse response) throws Exception {
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		user.setPassword(CryptographyUtil.md5(newPassword, "chenhao"));
		int resultTotal = userService.update(user);
		ResultDTO result = new ResultDTO();
		Gson gson = new Gson();
		if (resultTotal > 0) {
			result.setStatus(1);
		} else {
			result.setStatus(-1);
			result.setMessage("数据库,严重错误!!!!!!!");
		}
		ResponseUtil.write(response, gson.toJson(result));
		return null;
	}
	
	@RequestMapping("/manage")
	public String manage(Model model) throws Exception {
		model.addAttribute("pageTitle", "用户管理");
		model.addAttribute("title", "用户管理");
		return "pc/page/user/user_manage";
	}
	
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
		
		System.out.println("查询条件。。。。。" + map.toString());
		List<User> list = userService.list(map);
		Integer total = userService.getTotal(map);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		
		map.clear();
		map.put("data", list);
		map.put("count", total);
		map.put("code", 0);
		map.put("msg", "");
		ResponseUtil.write(response, gson.toJson(map));
		return null;
	}
	

	@RequestMapping("/delete")
	public String delete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)
			throws Exception {
		String[] idsStr = ids.split(",");
		Gson gson = new Gson();
		ResultDTO result = new ResultDTO();
		for (int i = 0; i < idsStr.length; i++) {
			userService.delete(Integer.parseInt(idsStr[i]));
		}
		result.setStatus(1);
		ResponseUtil.write(response, gson.toJson(result));
		return null;
	}

}
