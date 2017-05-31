package com.mm.mealapp.activity;

import org.json.JSONArray;
import org.json.JSONException;

import android.R.integer;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.miebo.utils.BaseActivity;
import com.miebo.utils.BaseUtil;
import com.miebo.utils.HttpUtil;
import com.miebo.utils.OnLineUser;
import com.miebo.utils.SPUtil;

/**
 * 登录界面
 * 
 * @author zlus
 * 
 */
public class LoginActivity extends BaseActivity {

	private Button btnLogin, btnRegister;
	private EditText etLoginID, etPassword;

	private CheckBox ckbSavePwd;

	private HttpUtil httpHelper;
	private ProgressDialog dialog;
	private loadAsyncTask loginAsyncTask;
	//我的订单中要用
	public static int id;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		findview();
		setListener();
		httpHelper = new HttpUtil();
		etLoginID.setText("test00");
		etPassword.setText("111111");
		PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY, "d75gLNsSLynSFb7QKePx7L2r");
	}

	@Override
	protected void onResume() {
		super.onResume();
		application.setLoginUser(null);
	}

	private void findview() {
		((TextView) findViewById(R.id.tvTopTitleCenter)).setText("登录");
		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnRegister = (Button) findViewById(R.id.btnRegister);
		etLoginID = (EditText) findViewById(R.id.etLoginID);
		etPassword = (EditText) findViewById(R.id.etPassword);
		ckbSavePwd = (CheckBox) findViewById(R.id.ckbSavePwd);

	}

	private void setListener() {
		btnLogin.setOnClickListener(new btnLoginOnClickListener());
		etLoginID.setText(SPUtil.get(LoginActivity.this, "loginid", ""));
		etPassword.setText(SPUtil.get(LoginActivity.this, "password", ""));
		btnRegister.setOnClickListener(this);
	}

	
	private class btnLoginOnClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			if (etLoginID.getText().length() == 0) {
				toastUtil.show("请输入账号");
				return;
			}
			if (etPassword.getText().length() == 0) {
				toastUtil.show("请输入密码");
				return;
			}

			BaseUtil.HideKeyboard(LoginActivity.this);
			loginAsyncTask = new loadAsyncTask();
			loginAsyncTask.execute("");

		}
	};

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
			String urlString = AppConstant.getUrl(getApplicationContext()) + "ServletService?Action=login";
			urlString += "&loginid=" + etLoginID.getText() + "&passwords=" + etPassword.getText();
			String json = httpHelper.HttpRequest(urlString);
			return json;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			loginAsyncTask = null;
			dialog.dismiss();
			if (result.trim().length() == 0) {
				toastUtil.show("登录失败");
				return;
			} else {
				SPUtil.set(LoginActivity.this, "loginid", etLoginID.getText().toString());
				if (ckbSavePwd.isChecked()) {
					SPUtil.set(LoginActivity.this, "password", etPassword.getText().toString());
				} else {
					SPUtil.set(LoginActivity.this, "password", "");
				}
				try {
					jsonArray = new JSONArray(result);
					jsonObject = jsonArray.getJSONObject(0);
					// 保存登录用户信息
					CommonApplication application = (CommonApplication) getApplicationContext();
					OnLineUser model = new OnLineUser();
					model.setId(jsonObject.getInt("id"));
					model.setLoginid(etLoginID.getText().toString());
					model.setName(jsonObject.getString("name"));
					application.setLoginUser(model);
					toastUtil.show(model.getName() + ",登录成功");
					id = model.getId();
					intent = new Intent(LoginActivity.this, FragmentActivity.class);
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
		switch (v.getId()) {
		case R.id.btnRegister:
			intent = new Intent(LoginActivity.this, RegisterActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

}
