package com.java.util;


/**
 * 字符串工具类
 * 判断读取的字符串空否
 * 
 * @author 流至-胡申俊
 *
 */
public class Stringutil {
	
	/**
	 * 判断是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEpty(String str) {
		
		if(str == null || "".equals(str.trim())) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 判断非空
	 * @param str
	 * @return
	 */
	
	public  static boolean isNotEmty(String str) {
		if(str!=null && !"".equals(str.trim())) {
			return true;
		}else {
			return false;
		}
		
	}
}
