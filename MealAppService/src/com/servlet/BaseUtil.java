package com.servlet;

public class BaseUtil {
	/**
	 * ÅÐ¶Ï×Ö·û´®ÊÇ·ñÎª¿Õ
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(CharSequence str) {
		if (str == null || "null".equals(str) || str.length() == 0)
			return true;
		else
			return false;
	}

	public static void LogII(Object iString) {
		System.out.println("System.out:" + String.valueOf(iString));

	}

}
