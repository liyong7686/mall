package com.liyong.until;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * ���ܹ���
 * @author Administrator
 *
 */
public class CryptographyUtil {
	
	public static void main(String[] args) throws Exception {
	 System.out.println(md5("1","chenhao"));
	}
	
	
	/**
	 * Md5����
	 * @param str  ���ܵ�����
	 * @param salt  ��ֵ 
	 */
	public static String md5(String str,String salt){
		return new Md5Hash(str,salt).toString();
	}
	
	
	
	
}
