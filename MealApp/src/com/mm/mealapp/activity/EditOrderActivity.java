package com.mm.mealapp.activity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.miebo.utils.AsyncImageLoader;
import com.miebo.utils.BaseActivity;
import com.miebo.utils.BaseUtil;
import com.mm.mealapp.adapter.AddressListViewAdapter;
import com.mm.mealapp.adapter.EachOrderAdapter;
import com.mm.mealapp.adapter.OrdersAdapter;
import com.mm.mealapp.api.ShopCar;
import com.mm.mealapp.api.address;
import com.mm.mealapp.api.orders;
import com.mm.mealapp.shopcar.activity.adapter.ShoppingCarAdapter;
import com.mm.mealapp.shopcar.db.ShoppingCarDb;
import com.mm.mealapp.shopcar.db.dao.ShoppingCarDao;

/**
 * 
 * @author zlus
 * 
 */
@SuppressLint("WrongCall")
public class EditOrderActivity extends BaseActivity {

	private AsyncImageLoader asyncImageLoader;
	private String serverUrl;
	private final int columns = 6;
	private Button btnTopTitleRight, btnTopTitleLeft;
	private HashMap<Integer, Integer> hashMap;
	private EditText etAmount;
	private TextView submitOrder;
	private RelativeLayout address;
	private ListView editOrder_addresslistview;
	private ListView editOrder_listview;
	private EditText editOrder_node_edit, deliveryTime;
	private RadioGroup editOrder_rg2, editOrder_rg;
	private RadioButton editOrder_deliveryWay1, editOrder_deliveryWay2, editOrder_payWay1, editOrder_payWay2;
	private String deliveryway, payway;
	public static List<ShopCar> list = new ArrayList<ShopCar>();
	private ListView shop_car_list;
	private ShoppingCarAdapter adapter;
	private ShoppingCarDao dao;
	private TextView tv_shop_order_count;
	private int intentlist;
	private TextView editOrder_listtitle;
	private double price;
	public static int orderid;
	private List<ShopCar> position = new ArrayList<ShopCar>();
	int listCount = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_order);
		application = (com.mm.mealapp.activity.CommonApplication) getApplicationContext();
		findview();
		dao = ShoppingCarDao.getInstance(this);
		list = dao.showAllShoppingCar();
		asyncImageLoader = new AsyncImageLoader(BitmapFactory.decodeResource(getResources(), R.drawable.pc_loading_fali));
		serverUrl = AppConstant.getRootUrl(this);
		hashMap = new HashMap<Integer, Integer>();
		address = (RelativeLayout) findViewById(R.id.editOrder_address);
		editOrder_addresslistview = (ListView) findViewById(R.id.editOrder_addresslistview);
		editOrder_node_edit = (EditText) findViewById(R.id.editOrder_node_edit);
		editOrder_rg2 = (RadioGroup) findViewById(R.id.editOrder_rg2);
		editOrder_rg = (RadioGroup) findViewById(R.id.editOrder_rg);
		editOrder_payWay1 = (RadioButton) findViewById(R.id.editOrder_payWay1);
		editOrder_payWay2 = (RadioButton) findViewById(R.id.editOrder_payWay2);
		editOrder_deliveryWay1 = (RadioButton) findViewById(R.id.editOrder_deliveryWay1);
		editOrder_deliveryWay2 = (RadioButton) findViewById(R.id.editOrder_deliveryWay2);
		editOrder_listtitle = (TextView) findViewById(R.id.editOrder_listtitle);
		deliveryTime = (EditText) findViewById(R.id.deliveryTime);
		address.setOnClickListener(this);
		new loadAddressAsyncTask().execute();
		editOrder_rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if (checkedId == editOrder_deliveryWay1.getId()) {
					deliveryway = "配送到家";
				} else {
					deliveryway = "到店自取";
				}
			}
		});
		editOrder_rg2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if (checkedId == editOrder_payWay1.getId()) {
					payway = "在线支付";
				} else {
					payway = "货到付款";
				}
			}
		});
		/* list = new ArrayList<ShopCar>(); */
		shop_car_list = (ListView) findViewById(R.id.editOrder_breakfastlist);
		adapter = new ShoppingCarAdapter(this, list, dao);
		shop_car_list.setAdapter(adapter);
		tv_shop_order_count = (TextView) findViewById(R.id.tv_shop_order_count);
		listCount = list.size();
		Integer counts = 0;
		Integer numbers = 0;
		for (int i = 0; i < listCount; i++) {
			counts += (list.get(i).getPrice() * list.get(i).getNumber());
			numbers += list.get(i).getNumber();
		}
		DecimalFormat df = new DecimalFormat("######0.00");
		tv_shop_order_count.setText("合计：￥" + df.format(counts));
		price = Double.parseDouble(df.format(counts));

	}

	private void findview() {
		((TextView) findViewById(R.id.tvTopTitleCenter)).setText("填写订单");

		btnTopTitleRight = (Button) findViewById(R.id.btnTopTitleRight);
		btnTopTitleRight.setText("提交");
		btnTopTitleRight.setVisibility(View.VISIBLE);
		btnTopTitleRight.setOnClickListener(this);

		btnTopTitleLeft = (Button) findViewById(R.id.btnTopTitleLeft);
		btnTopTitleLeft.setVisibility(View.VISIBLE);
		btnTopTitleLeft.setOnClickListener(this);
		btnTopTitleLeft.setText("返回");

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnTopTitleRight:
			new submitAsyncTask().execute();
			/* System.out.print("数量"+list2.size()); */
//			Log.i("fff", "数量222：" + list.size());
//			for (int i = 0; i < list.size(); i++) {
				new submitBreakfastAsyncTask(list).execute();
//			}
			break;
		case R.id.btnTopTitleLeft:
			finish();
			break;
		}

	}

	@SuppressWarnings("deprecation")
	private class submitAsyncTask extends AsyncTask<String, Integer, String> {
		@Override
		protected void onPreExecute() {
			dialog = ProgressDialog.show(EditOrderActivity.this, "提示", "处理中,请稍后..");

		}

		@Override
		protected String doInBackground(String... params) {
			/*
			 * Toast.makeText(getApplicationContext(), deliveryway,
			 * Toast.LENGTH_LONG).show();
			 */
			String urlString = AppConstant.getUrl(getApplicationContext()) + "ServletService";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("Action", "createorder");
			map.put("userid", user.getId());
			map.put("username", user.getName());
			map.put("deliveryway", deliveryway);
			map.put("deliverytime", deliveryTime.getText().toString());
			map.put("payway", payway);
			map.put("addressid", "1");
			map.put("node", editOrder_node_edit.getText().toString());
			map.put("price", price);
			map.put("orderid", StartActivity.id);
			/* orderid = (Integer) map.get("id"); */
			String result = httpHelper.HttpPost(urlString, map);
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			BaseUtil.LogII("result  " + result);
			dialog.dismiss();
			if (result != null && result.trim().equals("1")) {
				toastUtil.show("订单提交成功");
				/*
				 * intent = new Intent(EditOrderActivity.this,
				 * FragmentActivity.class); startActivity(intent);
				 */
				StartActivity.id++;
				if (payway.equals("在线支付")) {
					Intent in = new Intent(getApplicationContext(), PayActivity.class);
					startActivity(in);
				} else {
					intent = new Intent(EditOrderActivity.this, FragmentActivity.class);
					startActivity(intent);
				}
				finish();
			} else {
				toastUtil.show("订单提交失败");
			}
		}
	}

	@SuppressWarnings("deprecation")
	private class submitBreakfastAsyncTask extends AsyncTask<String, Integer, String> {
		private String result;

		public submitBreakfastAsyncTask(List<ShopCar> list) {
			position = list;
		}

		@Override
		protected void onPreExecute() {
			dialog = ProgressDialog.show(EditOrderActivity.this, "提示", "处理中,请稍后..");

		}

		@Override
		protected String doInBackground(String... params) {
			/*
			 * Toast.makeText(getApplicationContext(), deliveryway,
			 * Toast.LENGTH_LONG).show();
			 */
			String urlString = AppConstant.getUrl(getApplicationContext()) + "ServletService";
			Map<String, Object> map = new HashMap<String, Object>();
			for(int i=0;i<position.size();i++){
				map.put("Action", "createorder_breakfast");
				map.put("breakfastid", list.get(i).get_id());
				map.put("orderid", StartActivity.id - 1);
				map.put("title", list.get(i).getTitle());
				map.put("number", list.get(i).getNumber());
				map.put("price", list.get(i).getPrice());
				result = httpHelper.HttpPost(urlString, map);
			}
			
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			dialog.dismiss();
			if (result != null && result.trim().equals("1")) {
				toastUtil.show("订单中早餐提交成功");
				dao.deleteShoppingCarById();
				finish();
			} else {
				toastUtil.show("订单中早餐提交失败");
			}
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
				List<address> list = new Gson().fromJson(result, new TypeToken<List<address>>() {
				}.getType());
				AddressListViewAdapter adapter = new AddressListViewAdapter(EditOrderActivity.this, list);
				editOrder_addresslistview.setAdapter(adapter);
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
