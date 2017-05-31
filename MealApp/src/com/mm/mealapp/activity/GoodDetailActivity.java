package com.mm.mealapp.activity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.miebo.utils.AsyncImageLoader;
import com.miebo.utils.BaseActivity;
import com.miebo.utils.ToastUtil;
import com.mm.mealapp.adapter.CommentAdapter;
import com.mm.mealapp.api.ShopCar;
import com.mm.mealapp.api.comments;
import com.mm.mealapp.shopcar.db.dao.ShoppingCarDao;

/**
 * 
 * @author zlus
 * 
 */
public class GoodDetailActivity extends BaseActivity {
	private int id = 0;
	private ImageView imageView1;
	private AsyncImageLoader asyncImageLoader;
	private String serverUrl;

	private TextView tvIntro;

	private Button btnTopTitleRight, btnTopTitleLeft;
	private CommonApplication application;
	private ListView listview1;
	private CommentAdapter adapter;

	private ShoppingCarDao dao;
	private String goodTitle;
	private String goodPrice;
	@SuppressLint("SimpleDateFormat")
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Date time = new Date();

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {

		};
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gooddetail);
		application = (CommonApplication) getApplicationContext();
		findview();
		asyncImageLoader = new AsyncImageLoader(BitmapFactory.decodeResource(getResources(), R.drawable.pc_loading_fali));
		serverUrl = AppConstant.getRootUrl(this);
		if (getIntent() != null) {
			id = getIntent().getIntExtra("id", 0);
			new loadAsyncTask().execute(id + "");
		}

		dao = new ShoppingCarDao(this);
		/*// 添加购物车
		findViewById(R.id.btn_shopping_card_add).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				ToastUtil.getInstance(GoodDetailActivity.this).show("添加到购物车");
				boolean existGood = dao.isExistGood(goodTitle);
				if (existGood) {
					// 数据库中存在商品信息则修改商品的数量
					ShopCar shoppingCarInfo = dao.showShoppingCarInfoByTitle(goodTitle);
					Integer goodNumber = shoppingCarInfo.getNumber();
					goodNumber++;
					dao.updateShoppingNumber(goodNumber,dateFormat.format(time), goodTitle);
				} else {
					// 否则新增商品信息到购物车数据库中
					ShopCar info = new ShopCar();
					info.setTitle(goodTitle);
					info.setPrice(Integer.parseInt(goodPrice));
					info.setTime(dateFormat.format(time));
					info.setNumber(1);
					dao.addShoppingCar(info);
				}
			}
		});*/

	}

	private void findview() {

		imageView1 = (ImageView) findViewById(R.id.imageView1);

		tvIntro = (TextView) findViewById(R.id.tvIntro);

		btnTopTitleRight = (Button) findViewById(R.id.btnTopTitleRight);
		btnTopTitleRight.setText("加入购物车");
		btnTopTitleRight.setVisibility(View.VISIBLE);
		btnTopTitleRight.setOnClickListener(this);
		btnTopTitleRight.setTextSize(12);

		btnTopTitleLeft = (Button) findViewById(R.id.btnTopTitleLeft);
		btnTopTitleLeft.setVisibility(View.VISIBLE);
		btnTopTitleLeft.setOnClickListener(this);
		btnTopTitleLeft.setText("评论");
		listview1 = (ListView) findViewById(R.id.listview1);

	}

	private class loadAsyncTask extends AsyncTask<String, Integer, String> {
		@Override
		protected void onPreExecute() {
			dialog = ProgressDialog.show(GoodDetailActivity.this, "提示", "获取中,请稍后..");
		}

		@Override
		protected String doInBackground(String... params) {
			String urlString = AppConstant.getUrl(getApplicationContext()) + "ServletService?Action=getOneRow";
			urlString = urlString + "&ID=" + params[0] + "&Table=breakfast";
			String json = httpHelper.HttpRequest(urlString);
			return json;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			if (result.trim().length() > 0) {
				try {
					jsonArray = new JSONArray(result);
					jsonObject = jsonArray.getJSONObject(0);

					((TextView) findViewById(R.id.tvTopTitleCenter)).setText(jsonObject.getString("title"));
					((TextView) findViewById(R.id.tvTopTitleCenter)).setTextSize(16);
					if (!TextUtils.isEmpty(jsonObject.getString("img_url"))) {
						asyncImageLoader.loadBitmap(serverUrl + "UploadFile/" + jsonObject.getString("img_url"), imageView1);
					}
					goodTitle = jsonObject.getString("title");
					goodPrice = jsonObject.getString("price");
					String intro = "单价:￥" + jsonObject.getString("price") + "\n";
					intro += "数量:" + jsonObject.getString("amount") + "\n";
					intro += "简介:" + jsonObject.getString("intro") + "\n";
					intro += "评论:";
					tvIntro.setText(intro);
					new loadCommentAsyncTask().execute();// 获取评论
				} catch (JSONException e) {
					e.printStackTrace();
				}

			}
		}
	}

	private class loadCommentAsyncTask extends AsyncTask<String, Integer, String> {
		@Override
		protected void onPreExecute() {

		}

		@Override
		protected String doInBackground(String... params) {
			String urlString = AppConstant.getUrl(getApplicationContext()) + "ServletService?Action=getComment";
			urlString = urlString + "&breakfastid=" + id;
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
				List<comments> list = new Gson().fromJson(result, new TypeToken<List<comments>>() {
				}.getType());
				adapter = new CommentAdapter(GoodDetailActivity.this, list);
				listview1.setAdapter(adapter);
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 2 && resultCode == 1) {
			new loadCommentAsyncTask().execute();
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnTopTitleRight:
			ToastUtil.getInstance(GoodDetailActivity.this).show("添加到购物车");
			boolean existGood = dao.isExistGood(goodTitle);
			if (existGood) {
				// 数据库中存在商品信息则修改商品的数量
				ShopCar shoppingCarInfo = dao.showShoppingCarInfoByTitle(goodTitle);
				Integer goodNumber = shoppingCarInfo.getNumber();
				goodNumber++;
				dao.updateShoppingNumber(goodNumber,dateFormat.format(time), goodTitle);
			} else {
				// 否则新增商品信息到购物车数据库中
				ShopCar info = new ShopCar();
				info.setTitle(goodTitle);
				info.setPrice(Integer.parseInt(goodPrice));
				info.setTime(dateFormat.format(time));
				info.setNumber(1);
				dao.addShoppingCar(info);
			}
			/*intent = new Intent(GoodDetailActivity.this, EditOrderActivity.class);
			intent.putExtra("id", id);
			intent.putExtra("list", 2);
			startActivityForResult(intent, 1);*/
			break;
		case R.id.btnTopTitleLeft:
			intent = new Intent(GoodDetailActivity.this, CommentActivity.class);
			intent.putExtra("breakfastid", id);
			startActivityForResult(intent, 2);
			break;
		default:
			break;
		}

	}

}
