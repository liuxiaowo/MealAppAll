package com.example.chief;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.miebo.utils.BaseActivity;
import com.miebo.utils.SPUtil;

public class StartActivity extends BaseActivity {
	private Button btnOK;
	private EditText etIP;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (SPUtil.get(this, "IP", "").length() > 0) {
			intent = new Intent(StartActivity.this, LoginActivity.class);
			startActivity(intent);
			finish();
		} else {
			setContentView(R.layout.activity_start);
			findview();
		}
	}

	private void findview() {
		((TextView) findViewById(R.id.tvTopTitleCenter)).setText("配置IP地址");
		btnOK = (Button) findViewById(R.id.btnOK);
		etIP = (EditText) findViewById(R.id.etIP);
		btnOK.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnOK:
			if (etIP.getText().length() == 0) {
				toastUtil.show("请输入IP地址");
				return;
			}
			if (etIP.getText().length() < 12) {
				toastUtil.show("IP地址格式错误");
				return;
			}
			SPUtil.set(StartActivity.this, "IP", etIP.getText().toString());
			intent = new Intent(StartActivity.this, LoginActivity.class);
			startActivity(intent);
			finish();
			break;

		default:
			break;
		}

	}
}
