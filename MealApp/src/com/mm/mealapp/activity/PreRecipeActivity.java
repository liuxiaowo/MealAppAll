package com.mm.mealapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class PreRecipeActivity extends Activity implements OnClickListener {

	private ImageButton preRecipe_back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(1);
		setContentView(R.layout.activity_pre_recipe);
		preRecipe_back = (ImageButton)findViewById(R.id.preRecipe_back);
		preRecipe_back.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pre_recipe, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.preRecipe_back:
			Intent in = new Intent(getApplicationContext(),UpFinishPicActivity.class);
			startActivity(in);
			break;
		}
	}

}
