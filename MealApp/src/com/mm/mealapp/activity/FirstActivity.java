package com.mm.mealapp.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.miebo.utils.BaseActivity;
import com.mm.mealapp.adapter.BreakfastAdapter;
import com.mm.mealapp.api.breakfast;

public class FirstActivity extends BaseActivity implements OnClickListener {

	private TextView  timeCount;
	private Handler mHandler = new Handler();// 全局handler
	int time = 2*24*60*60;// 获取时间差;
	LinearLayout ll;
	private List<breakfast> list;
	private BreakfastAdapter adapter;
	private ListView listview1;
	private TextView tvTopTitleCenter;
	private String keyword = "";
	private LinearLayout search;
	private Button btnTopTitleRight;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		timeCount = (TextView) this.findViewById(R.id.showcount);
		search = (LinearLayout)findViewById(R.id.search);
		ll = (LinearLayout)findViewById(R.id.search);
		new Thread(new TimeCount()).start();// 开启线程
		ll.setOnClickListener(this);
		search.setOnClickListener(this);
		findview();
		new loadAsyncTask().execute(keyword);
	}

	class TimeCount implements Runnable
	{
		@Override
		public void run()
		{
			while (time > 0)// 整个倒计时执行的循环
			{
				time--;
				mHandler.post(new Runnable() // 通过它在UI主线程中修改显示的剩余时间
				{
					public void run()
					{
						timeCount.setText(getInterval(time));// 显示剩余
					}
				});
				try
				{
					Thread.sleep(1000);// 线程休眠一秒钟 这个就是倒计时的间隔时间
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			// 下面是倒计时结束逻辑
			mHandler.post(new Runnable()
			{
				@Override
				public void run()
				{
					timeCount.setText("活动已结束");
				}
			});
		}
	}
	 /**
		 * 设定显示文字
		 */
		public static String getInterval(int time)
		{
			String txt = null;
			if (time >= 0)
			{
				long day = time / (24 * 3600);// 天
				long hour = time % (24 * 3600) / 3600;// 小时
				long minute = time % 3600 / 60;// 分钟
				long second = time % 60;// 秒
				
				txt =" 剩余:" + day + "天" + hour + "小时" + minute + "分" + second + "秒";
			} 
			else
			{
				txt="已过期";
			}
			return txt;
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.first, menu);
		menu.add(0, 100, 0, "我的订单").setIcon(R.drawable.icon_application);
		menu.add(0, 101, 0, "修改密码").setIcon(R.drawable.icon_application);
		menu.add(0, 102, 0, "退出").setIcon(R.drawable.icon_application);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.search:
			search();
			break;
		case R.id.btnTopTitleRight:
			Intent in = new Intent(getApplicationContext(),ShakeBreakfastActivity.class);
			startActivity(in);
			break;
		}
	}
	
	private void findview() {
		tvTopTitleCenter = ((TextView) findViewById(R.id.tvTopTitleCenter));
		tvTopTitleCenter.setText("全部早餐");
		
		btnTopTitleRight = (Button) findViewById(R.id.btnTopTitleRight);
		btnTopTitleRight.setText("摇一摇");
		btnTopTitleRight.setTextSize(12);
		btnTopTitleRight.setVisibility(View.VISIBLE);
		btnTopTitleRight.setOnClickListener(this);

		listview1 = (ListView) findViewById(R.id.listview1);

	}

	private class loadAsyncTask extends AsyncTask<String, Integer, String> {
		@Override
		protected void onPreExecute() {
			dialog = ProgressDialog.show(FirstActivity.this, "提示", "获取中..");
		}

		@Override
		protected String doInBackground(String... params) {
			String json = null;

			serverUrl = AppConstant.getUrl(getApplicationContext()) + "ServletService?Action=getbreakfastlist&msg="
					+ params[0];

			json = httpHelper.HttpRequest(serverUrl);
			return json;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			dialog.dismiss();
			list = new ArrayList<breakfast>();
			if (result != null && result.trim().length() > 0) {
				try {
					jsonArray = new JSONArray(result);
					for (int i = 0; i < jsonArray.length(); i++) {
						jsonObject = jsonArray.getJSONObject(i);
						breakfast model = new breakfast();
						model.setId(jsonObject.getInt("id"));

						model.setIntro(jsonObject.getString("intro"));
						model.setImg_url(jsonObject.getString("img_url"));
						model.setTitle(jsonObject.getString("title"));
						model.setPrice(jsonObject.getDouble("price"));
						list.add(model);
					}

				} catch (JSONException e) {
					e.printStackTrace();
				}

			} else {
				toastUtil.show("没有数据");
			}
			adapter = new BreakfastAdapter(FirstActivity.this, list);
			listview1.setAdapter(adapter);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1 && resultCode == 1) {
			new loadAsyncTask().execute(tvTopTitleCenter.getText().toString(), keyword);
		}
	}

	private void search() {
		final EditText eText = new EditText(this);
		AlertDialog dialog = new AlertDialog.Builder(this).setTitle("搜索").setIcon(android.R.drawable.ic_dialog_info)
				.setView(eText).setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						keyword = eText.getText().toString();
						new loadAsyncTask().execute(keyword);
					}

				}).setNegativeButton("取消", null).create();
		dialog.show();
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case 100:
			intent = new Intent(FirstActivity.this, FragmentActivity.class);
			startActivity(intent);
			break;
		case 101:
			intent = new Intent(FirstActivity.this, UpdatePwdActivity.class);
			startActivity(intent);
			break;
		case 102:
			finish();
			System.exit(0);
			break;
		
		}
		return false;
	}
}



