package com.mm.mealapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.ImageButton;

import com.mm.mealapp.adapter.ExpandableListViewAdapter;

public class AllClassifyActivity extends Activity {

	ExpandableListView mExpandableListView;
	ExpandableListViewAdapter mExpandableListViewAdapter;
	ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(R.layout.activity_all_classify);
        mExpandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        mExpandableListViewAdapter = new ExpandableListViewAdapter(this);
        mExpandableListView.setAdapter(mExpandableListViewAdapter);
        mExpandableListView.expandGroup(0);
        back=(ImageButton)findViewById(R.id.ac_back);
//        openAll() ;
        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                // TODO Auto-generated method stub
//                Log.e("hefeng", "ExpandableListView GroupClickListener groupPosition=" + groupPosition);
                return false;
            }
        });
        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Log.e("hefeng", "ExpandableListView ChildClickListener groupPosition=" + groupPosition + "||childPosition=" + childPosition);
                return false;
            }
        });
        back.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.all_classify, menu);
        return true;
    }
    
}
