package com.mm.mealapp.shopcar.activity.adapter;

import java.text.DecimalFormat;
import java.util.List;

import com.mm.mealapp.activity.R;
import com.mm.mealapp.api.ShopCar;
import com.mm.mealapp.api.orders_breakfast;
import com.mm.mealapp.shopcar.db.dao.ShoppingCarDao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Order_BreakfastAdapter extends BaseAdapter {

	private Context mContext;
	private List<orders_breakfast> list;
	private ShoppingCarDao dao;

	public Order_BreakfastAdapter(Context context, List<orders_breakfast> list, ShoppingCarDao dao) {
		this.mContext = context;
		this.list = list;
		this.dao = dao;
	}

	public void flushDate(List<orders_breakfast> list) {
		this.list = list;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int postion, View convertView, ViewGroup parent) {
		orders_breakfast shopCar = list.get(postion);
		View view;
		ViewHodler hodler;
		if (convertView == null) {
			view = LayoutInflater.from(mContext).inflate(R.layout.shopping_car_list_item, null);
			hodler = new ViewHodler();
			hodler.tv_shop_car_item_title = (TextView) view.findViewById(R.id.tv_shop_car_item_title);
			hodler.tv_shop_car_item_price = (TextView) view.findViewById(R.id.tv_shop_car_item_price);
			hodler.tv_shop_car_item_number = (TextView) view.findViewById(R.id.tv_shop_car_item_number);
			view.setTag(hodler);
		} else {
			view = convertView;
			hodler = (ViewHodler) view.getTag();
		}
		DecimalFormat df = new DecimalFormat("######0.00");
		hodler.tv_shop_car_item_title.setText(shopCar.getTitle());
		hodler.tv_shop_car_item_price.setText("£¤" + df.format(shopCar.getPrice()));
		hodler.tv_shop_car_item_number.setText("ÊýÁ¿£º" + shopCar.getNumber());
		return view;
	}

	class ViewHodler {
		TextView tv_shop_car_item_title;
		TextView tv_shop_car_item_price;
		TextView tv_shop_car_item_number;
	}

}
