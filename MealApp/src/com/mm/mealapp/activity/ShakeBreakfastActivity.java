package com.mm.mealapp.activity;


import java.util.Random;

import android.content.Intent;
import android.widget.ImageView;

import com.mm.mealapp.util.ShakeUtils;
import com.mm.mealapp.util.ShakeUtils.OnShakeListener;



public class ShakeBreakfastActivity extends ShakeBaseActivity {
	@Override
	public void setContentView() {
		setContentView( R.layout.activity_shake_breakfast);
	}

	@Override
	public void findViews() {
		initShakeUtils( );
	}

	@Override
	public void getData() {
		
	}

	@Override
	public void showContent() {
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		mShakeUtils.onResume( );
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		mShakeUtils.onPause( );
	}
	
	private void initShakeUtils(){
		mShakeUtils = new ShakeUtils( this );
		mShakeUtils.setOnShakeListener( new OnShakeListener( ) {
			@Override
			public void onShake() {
				setShakeImage( );
			}
		});
	}
	
	private void setShakeImage( ){
		Random random = new Random();
		/*int id =(int)(Math.random()*6);*/
		Intent in = new Intent(getApplicationContext(),GoodDetailActivity.class);
		in.putExtra("id",Math.abs(random.nextInt(7))+1);
		startActivity(in);
		/*mShakeImageView.setBackgroundResource( mBeautys[ ( Math.abs(random.nextInt( ) ) )%mBeautys.length ] );*/
	}
	
	private ImageView mShakeImageView = null;
	private ShakeUtils mShakeUtils = null;
	/*private static final int[] mBeautys = new int[]{0,1,2};*/
}

