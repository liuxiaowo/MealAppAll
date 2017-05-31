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
		((TextView) findViewById(R.id.tvTopTitleCenter)).setText("�޸�����");
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
				toastUtil.show("�������˺�");
				return;
			}

			if (etPasswordOld.getText().length() == 0) {
				toastUtil.show("�����������");
				return;
			}
			if (etPassword.getText().length() == 0) {
				toastUtil.show("����������");
				return;
			}

			if (etPasswordOK.getText().length() == 0) {
				toastUtil.show("���ٴ���������");
				return;
			}
			if (!etPassword.getText().toString().equals(etPasswordOK.getText().toString())) {
				toastUtil.show("������������벻һ��");
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
			dialog.setTitle("��ʾ");
			dialog.setMessage("������,���Ժ�..");
			dialog.setCancelable(true);
			dialog.setButton("ȡ��", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					if (loginAsyncTask != null) {
						loginAsyncTask.cancel(true);
						loginAsyncTask = null;
						toastUtil.show("������ȡ��");
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
				toastUtil.show("�޸ĳɹ�");
				finish();
			} else if (result != null && result.trim().equals("-1")) {
				toastUtil.show("���������");
			} else {
				toastUtil.show("�޸�ʧ��");
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
