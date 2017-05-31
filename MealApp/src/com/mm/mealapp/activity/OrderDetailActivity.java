package com.mm.mealapp.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.miebo.utils.BaseActivity;
import com.mm.mealapp.api.orders_breakfast;
import com.mm.mealapp.shopcar.activity.adapter.Order_BreakfastAdapter;
import com.mm.mealapp.shopcar.db.dao.ShoppingCarDao;

public class OrderDetailActivity extends BaseActivity {

	private int orderid, number, id, goodid;
	private String goodTitle;
	private double goodPrice;
	private List<orders_breakfast> list;
	private ListView shop_breakfast_list;
	private Order_BreakfastAdapter adapter;
	private ShoppingCarDao dao;
	private TextView btnTopTitleLeft, tvTopTitleCenter, tvTitle, tvPrice, tvIntro;
	// 订单详情信息
	private String createTime;
	private String infos;
	private int state;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_detail);
		initView();
		if (getIntent() != null) {
			orderid = getIntent().getIntExtra("orderid", 0);
			createTime = getIntent().getStringExtra("createTime");
			infos = getIntent().getStringExtra("infos");
			state = getIntent().getIntExtra("state", 0);
			new loadAsyncTask().execute(orderid + "");
		}
		tvPrice.setText("" + infos);
		if (1 == state) {
			tvIntro.setText("订单状态:已经完成");
		} else if (0 == state) {
			tvIntro.setText("订单状态:进行中");
		} else {
			tvIntro.setText("订单状态:已取消");
		}
	}

	/**
	 * 初始化
	 */
	private void initView() {
		btnTopTitleLeft = (TextView) findViewById(R.id.btnTopTitleLeft);
		tvTopTitleCenter = (TextView) findViewById(R.id.tvTopTitleCenter);
		tvTitle = (TextView) findViewById(R.id.tvTitle);
		tvPrice = (TextView) findViewById(R.id.tvPrice);
		tvIntro = (TextView) findViewById(R.id.tvIntro);

		btnTopTitleLeft.setVisibility(View.VISIBLE);
		btnTopTitleLeft.setText("返回");
		// 返回
		btnTopTitleLeft.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});

		tvTopTitleCenter.setVisibility(View.VISIBLE);
		tvTopTitleCenter.setText("订单详情");

	}

	private class loadAsyncTask extends AsyncTask<String, Integer, String> {
		@Override
		protected void onPreExecute() {
			/*
			 * dialog = ProgressDialog.show(OrderDetailActivity.this, "提示",
			 * "获取中,请稍后..");
			 */
		}

		@Override
		protected String doInBackground(String... params) {
			String urlString = AppConstant.getUrl(getApplicationContext()) + "ServletService?Action=getorder_breakfast&orderid=" + orderid;
			Log.i("fff", "urlString:" + urlString);
			String json = httpHelper.HttpRequest(urlString);
			return json;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			list = new ArrayList<orders_breakfast>();
			shop_breakfast_list = (ListView) findViewById(R.id.shop_breakfast_list);
			dao = new ShoppingCarDao(OrderDetailActivity.this);
			if (result.trim().length() > 0) {
				try {
					jsonArray = new JSONArray(result);
					for (int i = 0; i < jsonArray.length(); i++) {
						jsonObject = jsonArray.getJSONObject(i);
						id = jsonObject.getInt("id");
						goodTitle = jsonObject.getString("title");
						goodPrice = jsonObject.getDouble("price");
						number = jsonObject.getInt("number");
						goodid = jsonObject.getInt("breakfastid");
						orders_breakfast model = new orders_breakfast();
						model.setId(id);
						model.setBreakfastid(goodid);
						model.setNumber(number);
						model.setPrice(goodPrice);
						model.setTitle(goodTitle);
						model.setOrderid(jsonObject.getInt("orderid"));
						list.add(model);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}

			}
			adapter = new Order_BreakfastAdapter(OrderDetailActivity.this, list, dao);
			shop_breakfast_list.setAdapter(adapter);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.order_detail, menu);
		return true;
	}

}
