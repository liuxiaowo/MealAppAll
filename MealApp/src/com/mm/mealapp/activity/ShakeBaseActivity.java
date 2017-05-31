package com.mm.mealapp.activity;

import android.os.Bundle;
import android.app.Activity;

public abstract class ShakeBaseActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}
	
	private void init(){
		setContentView( );
		findViews( );
		getData( );
		showContent( );
	}
	
	public abstract void setContentView();
	public abstract void findViews();
	public abstract void getData();
	public abstract void showContent();
}
