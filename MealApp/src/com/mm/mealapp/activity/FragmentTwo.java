package com.mm.mealapp.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.miebo.utils.HttpUtil;
import com.miebo.utils.ToastUtil;
import com.mm.mealapp.adapter.OrdersAdapter;
import com.mm.mealapp.api.orders;




@SuppressLint("NewApi")
public class FragmentTwo extends Fragment{
	private List<orders> list;
	private OrdersAdapter adapter;
	private ListView listview1;
	private TextView tvTopTitleCenter;
	private ProgressDialog dialog;
	private String serverUrl;
	private HttpUtil httpHelper = new HttpUtil();
    private ToastUtil toastUtil;
    private JSONArray jsonArray;
    private JSONObject jsonObject;
   
	@Override  
    public void onCreate(Bundle savedInstanceState) {  
        // TODO Auto-generated method stub  
        super.onCreate(savedInstanceState); 
        new loadAsyncTask().execute();
    }  


    @Override  
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState) {  
        // TODO Auto-generated method stub  
    	View v =inflater.inflate(R.layout.fragment_two, container, false);
    	tvTopTitleCenter = ((TextView) v.findViewById(R.id.tvTopTitleCenter));
		tvTopTitleCenter.setText("我的订单");
		
		listview1 = (ListView) v.findViewById(R.id.listview1);
		listview1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position,
					long arg3) {
				Intent in = new Intent(getActivity().getApplicationContext(),OrderDetailActivity.class);
				in.putExtra("orderid", list.get(position).getOrderid());
				in.putExtra("createTime", list.get(position).getCreatetime());
				in.putExtra("infos", "总额:" + list.get(position).getPrice()+"\n配送方式:"+list.get(position).getDeliveryway()+"\n配送时间:"+list.get(position).getDeliverytime()+"\n支付方式:"+list.get(position).getPayway()+"\n备注:"+list.get(position).getNode());
				in.putExtra("state", list.get(position).getStatus());
				startActivity(in);
			}
			
		});
		listview1.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				showContactDialog(position);
				return true;
			}

		});
        return v;  
    }  
	
	private class loadAsyncTask extends AsyncTask<String, Integer, String> {
		@Override
		protected void onPreExecute() {
			dialog = ProgressDialog.show(getActivity(), "提示", "获取中..");
		}

		@Override
		protected String doInBackground(String... params) {
			String json = null;

			serverUrl = AppConstant.getUrl(getActivity().getApplicationContext()) + "ServletService?Action=getmyorderslist&userid="
					+ LoginActivity.id;
			json = httpHelper.HttpRequest(serverUrl);
			return json;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			dialog.dismiss();
			list = new ArrayList<orders>();
			if (result != null && result.trim().length() > 0) {
				try {
					jsonArray = new JSONArray(result);
					for (int i = 0; i < jsonArray.length(); i++) {
						jsonObject = jsonArray.getJSONObject(i);
						orders model = new orders();
						model.setId(jsonObject.getInt("id"));
						model.setCreatetime(jsonObject.getString("createtime"));
						model.setPrice(jsonObject.getDouble("price"));
						model.setStatus(jsonObject.getInt("status"));
						model.setAddressid(jsonObject.getInt("addressid"));
						model.setDeliveryway(jsonObject.getString("deliveryway"));
						model.setDeliverytime(jsonObject.getString("deliverytime"));
						model.setPayway(jsonObject.getString("payway"));
						model.setNode(jsonObject.getString("node"));
						model.setOrderid(jsonObject.getInt("orderid"));
						list.add(model);
					}

				} catch (JSONException e) {
					e.printStackTrace();
				}

			} else {
				toastUtil.show("没有数据");
			}
			adapter = new OrdersAdapter(getActivity(), list);
			listview1.setAdapter(adapter);
		}
	}

	// 弹出上下文菜单
	private void showContactDialog(final int position) {
		String[] arg = new String[] { "取消订单" };
		new AlertDialog.Builder(getActivity()).setTitle("选择操作").setItems(arg, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case 0:// 取消
					new deleteAsyncTask().execute(list.get(position).getId() + "");
				}
			}
		}).show();
	}

	private class deleteAsyncTask extends AsyncTask<String, Integer, String> {
		@Override
		protected void onPreExecute() {
			dialog = ProgressDialog.show(getActivity(), "提示", "取消中,请稍后..");
		}

		@Override
		protected String doInBackground(String... params) {
			serverUrl = AppConstant.getUrl(getActivity().getApplicationContext()) + "ServletService?Action=cancelOrders&ID="
					+ params[0];
			String json = httpHelper.HttpRequest(serverUrl);
			return json;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			dialog.dismiss();
			if (result != null && result.trim().length() > 0) {
				toastUtil.show("取消成功");
				new loadAsyncTask().execute();
			} else {
				toastUtil.show("取消失败");

			}

		}
	}

}
