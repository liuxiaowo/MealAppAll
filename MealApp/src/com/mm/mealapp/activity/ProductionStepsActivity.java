package com.mm.mealapp.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mm.mealapp.adapter.StepsGridViewAdapter;


public class ProductionStepsActivity extends Activity implements OnClickListener {

	private RelativeLayout relativelayout;
	private GridView proSteps_gridview;
	private ArrayList<String> mNumList = new ArrayList<String>();
	private ArrayList<String> mNameList = new ArrayList<String>();  
    private ArrayList<Drawable> mDrawableList = new ArrayList<Drawable>(); 
    private StepsGridViewAdapter gridviewAdapter;
    private ImageButton proSteps_back;
    private TextView proSteps_next;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(1);
		setContentView(R.layout.activity_production_steps);
		relativelayout = (RelativeLayout)findViewById(R.id.proSteps_bottom);
		proSteps_gridview = (GridView)findViewById(R.id.proSteps_gridview);
		proSteps_back = (ImageButton)findViewById(R.id.proSteps_back);
		proSteps_next = (TextView)findViewById(R.id.proSteps_next);
		Intent intent=getIntent();
		String word = intent.getStringExtra("word");
		UpRecipeActivity.mNameListExample.add(word);
		UpRecipeActivity.mDrawableListExample.add(EditStepsActivity.drawable);
		for(int i =1;i<=EditStepsActivity.num;i++){
		mNumList.add("²½Öè"+i);
		mNameList.add(UpRecipeActivity.mNameListExample.get(i));
		mDrawableList.add(UpRecipeActivity.mDrawableListExample.get(i));
		gridviewAdapter = new StepsGridViewAdapter(getApplicationContext(), mNumList, mNameList, mDrawableList);
		proSteps_gridview.setAdapter(gridviewAdapter);
		}
		relativelayout.setOnClickListener(this);
		proSteps_back.setOnClickListener(this);
		proSteps_next.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.production_steps, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.proSteps_bottom:
			Intent in = new Intent(getApplicationContext(),EditStepsActivity.class);
			startActivity(in);
			break;
		case R.id.proSteps_back:
			Intent in2 = new Intent(getApplicationContext(),UpRecipeActivity.class);
			startActivity(in2);
			break;
		case R.id.proSteps_next:
			Intent in3 = new Intent(getApplicationContext(),UpFinishPicActivity.class);
			startActivity(in3);
			break;
		}
	}

}
