package com.mm.mealapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class SecondActivity extends Activity implements OnClickListener {

	private ImageButton add;
	private Button show_food,show_recipe;
	private int flag = 0;
	private RelativeLayout hot24;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		add = (ImageButton)findViewById(R.id.add_showFood);
		show_food = (Button)findViewById(R.id.show_greatfood);
		show_recipe = (Button)findViewById(R.id.show_recipe);
		hot24 = (RelativeLayout)findViewById(R.id.showFood_top);
		add.setOnClickListener(this);
		show_food.setOnClickListener(this);
		hot24.setOnClickListener(this);
		show_recipe.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.add_showFood:
			if(flag==0){
				add.setBackgroundResource(R.drawable.add_evil);
				show_food.setVisibility(View.VISIBLE);
				show_recipe.setVisibility(View.VISIBLE);
			}else if(flag==1){
				add.setBackgroundResource(R.drawable.add);
				show_food.setVisibility(View.INVISIBLE);
				show_recipe.setVisibility(View.INVISIBLE);
			}
			flag=(flag+1)%2;
			break;
		case R.id.show_greatfood:
			Intent in = new Intent(getApplicationContext(),AddPicActivity.class);
			startActivity(in);
			break;
		case R.id.showFood_top:
			Intent in2 = new Intent(getApplicationContext(),Hot24Activity.class);
			startActivity(in2);
			break;
		case R.id.show_recipe:
			Intent in3 = new Intent(getApplicationContext(),UpRecipeActivity.class);
			startActivity(in3);
			break;
		}
	}

}
