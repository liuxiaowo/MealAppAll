package com.mm.mealapp.shopcar.activity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.miebo.utils.ToastUtil;
import com.mm.mealapp.activity.EditOrderActivity;
import com.mm.mealapp.activity.R;
import com.mm.mealapp.api.ShopCar;
import com.mm.mealapp.shopcar.activity.adapter.ShoppingCarAdapter;
import com.mm.mealapp.shopcar.db.dao.ShoppingCarDao;

public class ShoppingCarActivity extends Activity implements OnClickListener {

	private ListView shop_car_list;
	private ShoppingCarAdapter adapter;
	private ShoppingCarDao dao;
	private List<ShopCar> list;
	private TextView tv_shop_car_count;
	private Button btn_shop_car_pay;
	private List<Integer> listIntegers;
	private ImageButton btn_shop_car_del;
	private Integer numbers;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shopping_car_list);
		list = new ArrayList<ShopCar>();
		dao = new ShoppingCarDao(this);
		list = dao.showAllShoppingCar();
		btn_shop_car_del = (ImageButton) findViewById(R.id.btn_shop_car_del);
		btn_shop_car_del.setOnClickListener(this);
		initView();
	}

	private void initView() {
		// 返回
		findViewById(R.id.btn_shop_car_back).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		shop_car_list = (ListView) findViewById(R.id.shop_car_list);
		tv_shop_car_count = (TextView) findViewById(R.id.tv_shop_car_count);
		btn_shop_car_pay = (Button) findViewById(R.id.btn_shop_car_pay);

		adapter = new ShoppingCarAdapter(this, list, dao);
		shop_car_list.setAdapter(adapter);

		int listCount = list.size();
		Integer counts = 0;
		numbers = 0;
		for (int i = 0; i < listCount; i++) {
			counts += (list.get(i).getPrice() * list.get(i).getNumber());
			numbers += list.get(i).getNumber();
		}

		DecimalFormat df = new DecimalFormat("######0.00");
		tv_shop_car_count.setText("合计：￥" + df.format(counts));
		btn_shop_car_pay.setText("去结算(" + numbers + ")");
		// 去结算商品
		btn_shop_car_pay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(numbers == 0){
					ToastUtil.getInstance(ShoppingCarActivity.this).show("请添加商品到购物车");
				} else {
					Intent intent = new Intent(getApplicationContext(), EditOrderActivity.class);
					intent.putExtra("id", 1);
					intent.putExtra("list", 1);
					startActivity(intent);
				}
				
			}
		});

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_shop_car_del:
			dao.deleteShoppingCarById();
			list = dao.showAllShoppingCar();
			adapter.flushDate(list);
			tv_shop_car_count.setText("合计：￥0.00");
			numbers = 0;
			btn_shop_car_pay.setText("去结算(" + numbers + ")");
			break;
		}
	}
}
