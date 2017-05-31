package com.mm.mealapp.activity;

import com.miebo.utils.ToastUtil;
import com.mm.mealapp.shopcar.activity.ShoppingCarActivity;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

@SuppressLint("NewApi")
public class FragmentThree extends Fragment implements OnClickListener {

	private LinearLayout address;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_three, container, false);
		address = (LinearLayout) v.findViewById(R.id.mine_linear2);
		address.setOnClickListener(this);
		// 跳转至购物车详情界面
		v.findViewById(R.id.mine_linear5).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getActivity(), ShoppingCarActivity.class);
				startActivity(intent);
			}
		});
		return v;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.mine_linear2:
			Intent in = new Intent(getActivity().getApplicationContext(), MineReceiveAddressActivity.class);
			startActivity(in);
			break;
		}
	}

}
