package com.mm.mealapp.activity;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.mm.mealapp.adapter.ViewPagerAdapter;


@SuppressLint("NewApi") 
public class FragmentOne extends Fragment implements OnPageChangeListener{
	public static ArrayList<View> viewlist;  
	public ViewPager hpViewPager;	
	public PagerAdapter adapter;	
	RadioGroup rg;
	RadioButton take_out,show_food;
	TextView tv1;
	TextView tv2;
	public static ArrayList<TextView> textColorlist; 
    LocalActivityManager manager;
    ImageButton user,classify_index;
	
	@Override  
    public void onCreate(Bundle savedInstanceState) {  
        // TODO Auto-generated method stub  
        super.onCreate(savedInstanceState); 
    }  


    @SuppressWarnings("deprecation")
	@Override  
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState) {  
        // TODO Auto-generated method stub  
    	View view = inflater.inflate(R.layout.fragment_one, container, false);
    	manager=new LocalActivityManager(getActivity(),false);
		manager.dispatchCreate(savedInstanceState); 
		hpViewPager=(ViewPager)view.findViewById(R.id.hpViewPager); //2
		rg=(RadioGroup)view.findViewById(R.id.rg);
		take_out=(RadioButton)view.findViewById(R.id.take_out);
		show_food=(RadioButton)view.findViewById(R.id.show_food);
		tv1=(TextView)view.findViewById(R.id.tv1);
		tv2=(TextView)view.findViewById(R.id.tv2);
		user=(ImageButton)view.findViewById(R.id.user_index);
		classify_index = (ImageButton)view.findViewById(R.id.classify_index);
		init();
		adapter=new ViewPagerAdapter(viewlist);//3
		hpViewPager.setAdapter(adapter);//4
		hpViewPager.setOnPageChangeListener(this);
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				for(int i=0;i<group.getChildCount();i++){
					if(group.getChildAt(i).getId()==checkedId){
						hpViewPager.setCurrentItem(i);
						setTextViewColor(i);
					}
				}
				
			}
			
		});
		user.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {
				Intent in=new Intent(getActivity().getApplicationContext(),RLbtnActivity.class);
				startActivity(in);
			}
		});
		classify_index.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(getActivity().getApplicationContext(),AllClassifyActivity.class);
				startActivity(in);
			}
		});
        return view; 
        
    }  
    public void setTextViewColor(int i) {
		for(int j=0;j<textColorlist.size();j++){
			if(j==i){
			textColorlist.get(j).setBackgroundColor(Color.rgb(255, 90, 96));
			take_out.setTextColor(Color.rgb(52, 50, 50));
			show_food.setTextColor(Color.rgb(255, 90, 96));
			}else{
			textColorlist.get(j).setBackgroundColor(Color.WHITE);
			take_out.setTextColor(Color.rgb(255, 90, 96));
			show_food.setTextColor(Color.rgb(52, 50, 50));
			}
		}
	}
	@SuppressWarnings("deprecation")
	public View getView(String id,Intent in){
		return manager.startActivity(id,in).getDecorView();
	}
	public void init(){
		viewlist=new ArrayList<View>(); //1
		Intent in1=new Intent(getActivity().getApplicationContext(),FirstActivity.class);
		Intent in2=new Intent(getActivity().getApplicationContext(),SecondActivity.class);
		View v1 = getView("First",in1);
		View v2 = getView("Second",in2);
		viewlist.add(v1);
		viewlist.add(v2);
		textColorlist=new ArrayList<TextView>();
		textColorlist.add(tv1);
		textColorlist.add(tv2);
		setTextViewColor(0);   //閸掓繂顬婇崠鏍ㄧ拨閸斻劍娼�
	}
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		setTextViewColor(arg0);
	}

}
