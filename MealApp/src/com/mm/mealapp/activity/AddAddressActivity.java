package com.mm.mealapp.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.miebo.utils.BaseActivity;
import com.miebo.utils.BaseUtil;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddAddressActivity extends BaseActivity implements OnClickListener {

	private ImageButton addAddress_back,addAddress_ok;
	private EditText addAddress_nameEdit,addAddress_phoneEdit,addAddress_rough_edit,addAddress_detail_edit;
	private RadioGroup addAddress_sex;
	private RadioButton sex;
	public static ArrayList<String> namelist = new ArrayList<String>(); 
	public static ArrayList<String> phonelist = new ArrayList<String>(); 
	public static ArrayList<String> addresslist = new ArrayList<String>(); 
	private int userid;
	private loadAsyncTask loginAsyncTask;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(1);
		setContentView(R.layout.activity_add_address);
		addAddress_back = (ImageButton)findViewById(R.id.addAddress_back);
		addAddress_ok = (ImageButton)findViewById(R.id.addAddress_ok);
		addAddress_nameEdit = (EditText)findViewById(R.id.addAddress_nameEdit);
		addAddress_sex = (RadioGroup)findViewById(R.id.addAddress_sex);
		sex = (RadioButton)findViewById(addAddress_sex.getCheckedRadioButtonId());
		addAddress_phoneEdit = (EditText)findViewById(R.id.addAddress_phoneEdit);
		addAddress_rough_edit = (EditText)findViewById(R.id.addAddress_rough_edit);
		addAddress_detail_edit = (EditText)findViewById(R.id.addAddress_detail_edit);
		addAddress_back.setOnClickListener(this);
		addAddress_ok.setOnClickListener(this);
		userid = LoginActivity.id;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_address, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.addAddress_back:
			Intent in = new Intent(getApplicationContext(),MineReceiveAddressActivity.class);
			startActivity(in);
			break;
		case R.id.addAddress_ok:
			String name = addAddress_nameEdit.getText().toString();
			String checked_sex = sex.getText().toString();
			String phone = addAddress_phoneEdit.getText().toString();
			String rough_address = addAddress_rough_edit.getText().toString();
			String detail_address = addAddress_detail_edit.getText().toString();
			if((!name.equals(""))&&(!checked_sex.equals(""))&&(!phone.equals(""))&&(!rough_address.equals(""))&&(!detail_address.equals(""))){
			Intent in2 = new Intent(getApplicationContext(),MineReceiveAddressActivity.class);
			namelist.add(name+checked_sex);
			phonelist.add(phone);
			addresslist.add(rough_address+detail_address);
			startActivity(in2);
			
			BaseUtil.HideKeyboard(AddAddressActivity.this);
			loginAsyncTask = new loadAsyncTask();
			loginAsyncTask.execute("");
			}else{
				Toast.makeText(getApplicationContext(), "内容不能为空", Toast.LENGTH_LONG).show();
			}
			break;
		}
		
		
	}
	@SuppressWarnings("deprecation")
	private class loadAsyncTask extends AsyncTask<String, Integer, String> {
		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(AddAddressActivity.this);
			dialog.setTitle("提示");
			dialog.setMessage("处理中,请稍后..");
			dialog.setCancelable(true);
			dialog.setButton("取消", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					if (loginAsyncTask != null) {
						loginAsyncTask.cancel(true);
						loginAsyncTask = null;
						toastUtil.show("操作被取消");
					}
				}
			});
			dialog.show();

		}

		@Override
		protected String doInBackground(String... params) {
			String urlString = AppConstant.getUrl(getApplicationContext()) + "ServletService";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("Action", "createaddress");
			map.put("userid", userid);
			map.put("realname", addAddress_nameEdit.getText());
			map.put("sex", sex.getText());
			map.put("phone", addAddress_phoneEdit.getText());
			map.put("address",addAddress_rough_edit.getText().toString()+addAddress_detail_edit.getText().toString());
			String result = httpHelper.HttpPost(urlString, map);
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			loginAsyncTask = null;
			dialog.dismiss();
			if (result != null && result.trim().equals("1")) {
				toastUtil.show("添加成功");
				setResult(1);
				finish();
			} else {
				toastUtil.show("添加失败");
			}
		}
	}


}
