package com.liyong.until;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;


public class ResponseUtil {

	public static void write(HttpServletResponse response,Object o)throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
<<<<<<< HEAD
		System.out.println(o.toString());
=======
>>>>>>> branch 'master' of https://github.com/liyong7686/mall.git
		out.println(o.toString());
		out.flush();
		out.close();
	}
}
