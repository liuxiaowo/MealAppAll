package com.mm.mealapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class RLbtnActivity extends Activity {

	ImageButton back,RL_register,RL_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(R.layout.activity_rlbtn);
        back=(ImageButton)findViewById(R.id.RL_back);
        RL_register = (ImageButton) findViewById(R.id.RL_register);
        RL_login = (ImageButton) findViewById(R.id.RL_login);
        back.setOnClickListener(new OnClickListener() {	
			public void onClick(View v) {
				Intent in = new Intent(getApplicationContext(),FragmentActivity.class);
				startActivity(in);
			}
		});
        RL_register.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(getApplicationContext(),RegisterActivity.class);
				startActivity(in);
			}
		});
        RL_login.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(getApplicationContext(),LoginActivity.class);
				startActivity(in);
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.rlbtn, menu);
        return true;
    }
    
}
