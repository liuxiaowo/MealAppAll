package com.servlet;

public class BaseUtil {
	/**
	 * �ж��ַ����Ƿ�Ϊ��
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
