package com.mm.mealapp.activity;

import java.util.HashMap;
import java.util.Map;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.miebo.utils.BaseActivity;
import com.miebo.utils.BaseUtil;
import com.miebo.utils.HttpUtil;

/**
 * 
 * @author zlus
 * 
 */
public class UpdatePwdActivity extends BaseActivity {

	private Button btnLogin, btnRegister;
	private EditText etLoginID, etPasswordOld, etPassword, etPasswordOK;

	private HttpUtil httpHelper;
	private ProgressDialog dialog;
	private loadAsyncTask loginAsyncTask;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_updpwd);
		findview();
		setListener();
		httpHelper = new HttpUtil();

	}

	private void findview() {
		((TextView) findViewById(R.id.tvTopTitleCenter)).setText("修改密码");
		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnRegister = (Button) findViewById(R.id.btnRegister);
		etLoginID = (EditText) findViewById(R.id.etLoginID);
		etPassword = (EditText) findViewById(R.id.etPassword);
		etPasswordOK = (EditText) findViewById(R.id.etPasswordOK);
		etPasswordOld = (EditText) findViewById(R.id.etPwOld);

	}

	private void setListener() {
		btnRegister.setOnClickListener(new btnRegisterOnClickListener());

		btnLogin.setOnClickListener(this);
	}

	private class btnRegisterOnClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			if (etLoginID.getText().length() == 0) {
				toastUtil.show("请输入账号");
				return;
			}

			if (etPasswordOld.getText().length() == 0) {
				toastUtil.show("请输入旧密码");
				return;
			}
			if (etPassword.getText().length() == 0) {
				toastUtil.show("请输入密码");
				return;
			}

			if (etPasswordOK.getText().length() == 0) {
				toastUtil.show("请再次输入密码");
				return;
			}
			if (!etPassword.getText().toString().equals(etPasswordOK.getText().toString())) {
				toastUtil.show("两次输入的密码不一致");
				return;
			}

			BaseUtil.HideKeyboard(UpdatePwdActivity.this);
			loginAsyncTask = new loadAsyncTask();
			loginAsyncTask.execute("");

		}
	};

	@SuppressWarnings("deprecation")
	private class loadAsyncTask extends AsyncTask<String, Integer, String> {
		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(UpdatePwdActivity.this);
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
			map.put("Action", "updatePwd");
			map.put("loginid", etLoginID.getText());
			map.put("passwords", etPasswordOld.getText());
			map.put("passwords_new", etPasswordOK.getText());

			String result = httpHelper.HttpPost(urlString, map);
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			BaseUtil.LogII("result " + result);
			loginAsyncTask = null;
			dialog.dismiss();
			if (result != null && result.trim().equals("1")) {
				toastUtil.show("修改成功");
				finish();
			} else if (result != null && result.trim().equals("-1")) {
				toastUtil.show("旧密码错误");
			} else {
				toastUtil.show("修改失败");
			}
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnLogin:
			finish();
			break;

		default:
			break;
		}
	}

}
