package com.mm.mealapp.activity;

import android.content.Context;

import com.miebo.utils.SPUtil;

public class AppConstant {
	/**
	 * 获取服务端Url根目录
	 * 
	 * @param context
	 * @return
	 */
	public static String getRootUrl(Context context) {
		return "http://" + SPUtil.get(context, "IP", "") + "/MealAppService/";
	}

	/**
	 * 获取服务端Url servlet 目录
	 * 
	 * @param context
	 * @return
	 */
	public static String getUrl(Context context) {
		return getRootUrl(context) + "servlet/";
	}

}
