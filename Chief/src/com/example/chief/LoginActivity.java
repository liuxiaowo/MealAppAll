package com.example.chief;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.api.chief;
import com.example.utils.CodeUtils;
import com.miebo.utils.BaseActivity;
import com.miebo.utils.BaseUtil;
import com.miebo.utils.HttpUtil;
import com.miebo.utils.SPUtil;





public class LoginActivity extends BaseActivity implements OnClickListener {

	private ImageView chief_code_img;
	private Button chief_register;
	private Spinner sp;
	private ArrayList<String> data_list;
	private ArrayAdapter<String> arr_adapter;
	private HttpUtil httpHelper;
	private ProgressDialog dialog;
	private loadAsyncTask loginAsyncTask;
	public static int id;
	private EditText chief_user_name,chief_pw_edit;
	private Button loginButton;
	private int position;
    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(R.layout.activity_login);
        chief_code_img = (ImageView)findViewById(R.id.chief_code_img);
        chief_register = (Button)findViewById(R.id.chief_register);
        sp = (Spinner)findViewById(R.id.register_spinner);
        chief_code_img.setImageBitmap(CodeUtils.getInstance().createBitmap());
        chief_code_img.setOnClickListener(this);
        chief_register.setOnClickListener(this);
        data_list = new ArrayList<String>();
        data_list.add("厨师");
        data_list.add("配送员");
        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(arr_adapter);
        httpHelper = new HttpUtil();
        chief_user_name = (EditText)findViewById(R.id.chief_user_name);
        chief_pw_edit = (EditText)findViewById(R.id.chief_pw_edit);
        chief_user_name.setText(SPUtil.get(LoginActivity.this, "loginid", ""));
        chief_pw_edit.setText(SPUtil.get(LoginActivity.this, "password", ""));
        loginButton = (Button)findViewById(R.id.chief_login);
        loginButton.setOnClickListener(this);
        sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				position = arg2;
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @SuppressWarnings("deprecation")
	private class loadAsyncTask extends AsyncTask<String, Integer, String> {
		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(LoginActivity.this);
			dialog.setTitle("提示");
			dialog.setMessage("登录中,请稍后..");
			dialog.setCancelable(true);
			dialog.setButton("取消", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					if (loginAsyncTask != null) {
						loginAsyncTask.cancel(true);
						loginAsyncTask = null;
						toastUtil.show("登录被取消");
					}
				}
			});
			dialog.show();

		}

		@Override
		protected String doInBackground(String... params) {
			String urlString = AppConstant.getUrl(getApplicationContext()) + "ServletService?Action=chieflogin";
			urlString = urlString + "&ID=" + params[0] + "&Table=chief";
			String json = httpHelper.HttpRequest(urlString);
			return json;
		}

		@Override
		protected void onPostExecute(String result) {
			Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
			super.onPostExecute(result);
			loginAsyncTask = null;
			dialog.dismiss();
			if (result.trim().length() == 0) {
				toastUtil.show("登录失败");
				return;
			} else {
				try {
					jsonArray = new JSONArray(result);
					jsonObject = jsonArray.getJSONObject(0);
					toastUtil.show(jsonObject.getString("loginid") + ",登录成功");
					id = jsonObject.getInt("id");
					intent = new Intent(LoginActivity.this, OrderActivity.class);
					startActivity(intent);
					LoginActivity.this.finish();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.chief_code_img:
			chief_code_img.setImageBitmap(CodeUtils.getInstance().createBitmap());
			break;
		case R.id.chief_register:
			Intent in = new Intent(getApplicationContext(),RegisterActivity.class);
			startActivity(in);
			break;
		case R.id.chief_login:
			if (chief_user_name.getText().length() == 0) {
				toastUtil.show("请输入账号");
				return;
			}
			if (chief_pw_edit.getText().length() == 0) {
				toastUtil.show("请输入密码");
				return;
			}
			BaseUtil.HideKeyboard(LoginActivity.this);
			/*if(position==0){*/
			loginAsyncTask = new loadAsyncTask();
			loginAsyncTask.execute("");
				/*Toast.makeText(getApplicationContext(), "厨师",Toast.LENGTH_LONG).show();*/
			/*}else{
				Toast.makeText(getApplicationContext(), "配送员",Toast.LENGTH_LONG).show();
			}*/
			break;
		}
	}
    
}
