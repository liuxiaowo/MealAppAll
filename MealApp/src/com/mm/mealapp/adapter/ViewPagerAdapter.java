package com.mm.mealapp.adapter;

import java.util.ArrayList;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

public class ViewPagerAdapter extends PagerAdapter{
	ArrayList<View> viewlist;
	//构�?函数
	public ViewPagerAdapter(ArrayList<View> viewlist) {
		this.viewlist=viewlist;
	}
	//view个数
	@Override
	public int getCount() {
		return viewlist.size();
	}
	//判断填充的视图是否为Object对象
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0==arg1;
	}
	//�?��视图
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewPager)container).removeView(viewlist.get(position));
	}
	//创建视图
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		((ViewPager)container).addView(viewlist.get(position));
		return viewlist.get(position);
	}
}
