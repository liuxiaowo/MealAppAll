package com.mm.mealapp.activity;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.mm.mealapp.adapter.ListViewAdapter;

public class UpRecipeActivity extends Activity implements OnClickListener {

	private Spinner difficulty,time;
	private ArrayList<String> data_list,data_time;
	private ArrayAdapter<String> arr_adapter;
	private ImageButton upRecipe_add_food,upRecipe_back;
	private EditText dialog_materital,dialog_useLevel;
	private ListView listview;
	private ArrayList<HashMap<String,String>> list;
	private String materital,uselevel;
	public static View layout;
	public static  ArrayList<String> mNameListExample = new ArrayList<String>();  
	public static ArrayList<Drawable> mDrawableListExample = new ArrayList<Drawable>(); 
	private TextView upRecipe_next;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(1);
		setContentView(R.layout.activity_up_recipe);
		difficulty = (Spinner)findViewById(R.id.upRecipe_difficulty);
		time = (Spinner)findViewById(R.id.upRecipe_time);
		upRecipe_add_food = (ImageButton)findViewById(R.id.upRecipe_add_food);
		listview = (ListView)findViewById(R.id.upRecipe_addOneFood); 
		upRecipe_next = (TextView)findViewById(R.id.upRecipe_next);
		upRecipe_back = (ImageButton)findViewById(R.id.upRecipe_back);
		list = new ArrayList<HashMap<String,String>>();
		//����
        data_list = new ArrayList<String>();
        data_list.add("�����Ѷ�");
        data_list.add("�м��Ѷ�");
        data_list.add("�߼��Ѷ�");    
        //������
        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
        //������ʽ
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //����������
        difficulty.setAdapter(arr_adapter);
        //����
        data_time = new ArrayList<String>();
        data_time.add("���10min");
        data_time.add("���10~30min");
        data_time.add("���30min~1h");
        data_time.add("���1h����");
        //������
        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_time);
        //������ʽ
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //����������
        time.setAdapter(arr_adapter);
        upRecipe_add_food.setOnClickListener(this);
        upRecipe_next.setOnClickListener(this);
        upRecipe_back.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.up_recipe, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.upRecipe_add_food:
			LayoutInflater inflater = getLayoutInflater();
		    layout = inflater.inflate(R.layout.addfood_dialog,(ViewGroup) findViewById(R.id.dialog));
		    new AlertDialog.Builder(this).setTitle("���ʳ��").setView(layout).setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog_materital = (EditText)layout.findViewById(R.id.dialog_materital);
				    dialog_useLevel = (EditText)layout.findViewById(R.id.dialog_useLevel);
				    materital = dialog_materital.getText().toString();
				    uselevel = dialog_useLevel.getText().toString();
				    HashMap<String,String> temp = new HashMap<String,String>();  
			        temp.put("materital",materital);  
			        temp.put("userLevel",uselevel);  
			        list.add(temp); 
			        ListViewAdapter adapter = new ListViewAdapter(UpRecipeActivity.this, list);  
			 	    listview.setAdapter(adapter); 
				}
			}).setNegativeButton("ȡ��", null).show();
			break;
		case R.id.upRecipe_next:
			Intent in = new Intent(getApplicationContext(),ProductionStepsActivity.class);
			startActivity(in);
			break;
		case R.id.upRecipe_back:
			Intent in2 = new Intent(getApplicationContext(),FragmentActivity.class);
			startActivity(in2);
			break;
		}
	}

}
