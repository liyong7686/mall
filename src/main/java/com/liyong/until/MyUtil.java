package com.liyong.until;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import sun.misc.BASE64Decoder;

public class MyUtil {
	
	public static void main(String s[]){
		//Date date = longToDate(1508375108, "yyyy-MM-dd HH:mm");
		//1386665346113
		System.out.println(new Date().getTime()/1000);
		String str = 12+"";
		System.out.println(bu0(5,4));
	}
	
	
	/**
	 * ȡ���û�ip
	 */
	public static String getRemoteAddress(HttpServletRequest request) {
		 String ip = request.getHeader("x-forwarded-for"); 
		 // System.out.println("x-forwarded-for ip: " + ip);
	        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {  
	            // ��η���������ж��ipֵ����һ��ip������ʵip
	            if( ip.indexOf(",")!=-1 ){
	                ip = ip.split(",")[0];
	            }
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("Proxy-Client-IP");  
	            //  System.out.println("Proxy-Client-IP ip: " + ip);
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("WL-Proxy-Client-IP");  
	            //  System.out.println("WL-Proxy-Client-IP ip: " + ip);
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("HTTP_CLIENT_IP");  
	            //  System.out.println("HTTP_CLIENT_IP ip: " + ip);
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
	            // System.out.println("HTTP_X_FORWARDED_FOR ip: " + ip);
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("X-Real-IP");  
	           // System.out.println("X-Real-IP ip: " + ip);
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getRemoteAddr();  
	            //  System.out.println("getRemoteAddr ip: " + ip);
	        } 
	        //System.out.println("��ȡ�ͻ���ip: " + ip);
	        return ip;  
		/*String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getRemoteAddr();
		}
		return ip;*/
	}

	/**
	 * �Ѵ�����string ids ��� List<Integer> ids = new ArrayList<Integer>() ����
	 * ��Ϊmybatis�鷶Χ,Ҫ�õĵ���
	 */
	public static List<Integer> Str_ids_To_ListInteger_ids(String ids) {
		List<Integer> ListInteger_ids = new ArrayList<Integer>();
		String[] arr = ids.split(",");
		for (String i : arr) {
			// ��֤�ǲ�������
			if (i.matches("\\d+")) {
				ListInteger_ids.add(Integer.parseInt(i));
			}
		}
		return ListInteger_ids;
	}

	/**
	 * ����������� ��ѡ�� ʱ�õ� �ж�id�ڲ���ids֮�ڡ�����ڷ���true
	 */
	public static boolean existStrArr(String id, String[] ids) {
		for (int i = 0; i < ids.length; i++) {
			if (ids[i].equals(id)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * ���û���menus����һ�ߣ����ǵ�һЩ����Ĳ˵�
	 */
	public static String filterMenuIds(String[] ids) {
		String subids = "500";
		String[] arr = arrContrast(ids, subids.split(","));
		return org.apache.commons.lang.StringUtils.join(arr, ',');
	}

	/**
	 * ��arr1Ϊ ��׼ ��arr2�����ݴ�arr�й��ǵ���ɾ�� �����µ�arr
	 */
	private static String[] arrContrast(String[] arr1, String[] arr2) {
		List<String> list = new LinkedList<String>();
		for (String str : arr1) { // �����һ������,list�����ֵΪ1,2,3,4
			if (!list.contains(str)) {
				list.add(str);
			}
		}
		for (String str : arr2) { // ����ڶ���������ں͵�һ��������ͬ��ֵ����ɾ��
			if (list.contains(str)) {
				list.remove(str);
			}
		}
		String[] result = {}; // ����������
		return list.toArray(result); // List to Array
	}
	
	/**
	 * �Ѷ������ ����ת��string
	 */
	public static String listToString(List list, char separator) {
		return org.apache.commons.lang.StringUtils.join(list.toArray(), separator);
	}
	
	// ��image str ת��ͼƬ ��������
	public static boolean GenerateImage(String imgStr, String Folder, String imgFilePath) {// ���ֽ������ַ���
		if (imgStr == null) // ͼ������Ϊ��
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64����
			byte[] bytes = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {
					// �����쳣����
					bytes[i] += 256;
				}
			}

			// �����ļ���
			makeDirs(Folder);
			// ����jpegͼƬ
			OutputStream out = new FileOutputStream(Folder + "\\" + imgFilePath);

			out.write(bytes);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean makeDirs(String filePath) {
		if (filePath == null || filePath.isEmpty()) {
			return false;
		}
		File folder = new File(filePath);
		if (folder.exists()) {// IsDirectory( ) �ж��ļ����Ƿ����
			return true;
		} else {
			return folder.mkdirs();
		}
	}
	
	
	/**
	 * ��longת��date ���long������13λ
	 * �����10λ�Ļ���Ҫ*1000
	 * @param times
	 * @param formatDate
	 * @return
	 */
	public static Date longToDate(long times, String formatDate) {
		// "yyyy-MM-dd HH:mm:ss"
		SimpleDateFormat format = new SimpleDateFormat(formatDate);
		Long time = new Long(times);
		String d = format.format(time);
		Date date = null;
		try {
			date = format.parse(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	
	/**
	 * @param num      ��Ҫ��0������
	 * @param count    ����λ��    ���ֲ�����λ����������0����
	 */
	public static String bu0(Integer num,int count){
	    String str = String.format("%0"+count+"d", num);   
		return str.trim();
	}
	
	
	/**
	 * 360����� =AppleWebKit
	 * �Ա������ =AppleWebKit
	 * ���=Firefox
	 * �ѹ�=AppleWebKit
	 * qq�����=AppleWebKit
	 * 
	 * @return
	 */
	public static boolean checkUserAgent(String UserAgent){
		boolean falg = false;
		if(UserAgent.indexOf("AppleWebKit")!=-1){
			falg = true;
		}
		if(UserAgent.indexOf("Firefox")!=-1){
			falg = true;
		}
		return falg;
	}
    
	
}
