package com.mm.mealapp.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.miebo.utils.BaseActivity;
import com.mm.mealapp.adapter.AddressListViewAdapter;
import com.mm.mealapp.api.address;


public class MineReceiveAddressActivity extends BaseActivity implements OnClickListener {

	private RelativeLayout mineAddress_add;
	private ListView listview;
	private TextView manager;
	private ImageButton mineAddress_back;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(1);
		setContentView(R.layout.activity_mine_receive_address);
		mineAddress_add = (RelativeLayout)findViewById(R.id.mineAddress_add);
		listview = (ListView)findViewById(R.id.mineAddress_listview);
		manager = (TextView)findViewById(R.id.mineAddress_manager);
		mineAddress_back = (ImageButton)findViewById(R.id.mineAddress_back);
		/*AddressListViewAdapter adapter = new AddressListViewAdapter(getApplicationContext(),AddAddressActivity.namelist, AddAddressActivity.phonelist, AddAddressActivity.addresslist);
		listview.setAdapter(adapter);*/
		mineAddress_add.setOnClickListener(this);
		manager.setOnClickListener(this);
		mineAddress_back.setOnClickListener(this);
		new loadAddressAsyncTask().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mine_receive_address, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.mineAddress_add:
			Intent in = new Intent(getApplicationContext(),AddAddressActivity.class);
			startActivity(in);
			break;
		case R.id.mineAddress_manager:
			AddressListViewAdapter.flag++;
			break;
		case R.id.mineAddress_back:
			Intent in2 = new Intent(getApplicationContext(),FragmentActivity.class);
			startActivity(in2);
		}
	}

	private class loadAddressAsyncTask extends AsyncTask<String, Integer, String> {
		@Override
		protected void onPreExecute() {

		}

		@Override
		protected String doInBackground(String... params) {
			String urlString = AppConstant.getUrl(getApplicationContext()) + "ServletService?Action=getAddress";
			urlString = urlString + "&userid=" + LoginActivity.id;
			String json = httpHelper.HttpRequest(urlString);
			return json;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if (dialog != null && dialog.isShowing()) {
				dialog.dismiss();
			}
			if (result.trim().length() > 0) {
				List<address> list = new Gson().fromJson(result, new TypeToken<List<address>>() {}.getType());
				AddressListViewAdapter adapter = new AddressListViewAdapter(MineReceiveAddressActivity.this, list);
				listview.setAdapter(adapter);
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 2 && resultCode == 1) {
			new loadAddressAsyncTask().execute();
		}

	}
}
