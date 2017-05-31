package com.mm.mealapp.activity;

public class CommonApplication extends com.miebo.utils.CommonApplication {

	public static final String strKeyBaiduMap = "F32d0b874fbade29187984040945de4e";
	private boolean isRefreshComment;

	@Override
	public void onCreate() {
		super.onCreate();

	}

	public boolean isRefreshComment() {
		return isRefreshComment;
	}

	public void setRefreshComment(boolean isRefreshComment) {
		this.isRefreshComment = isRefreshComment;
	}

}
