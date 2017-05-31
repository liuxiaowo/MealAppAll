package com.mm.mealapp.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.miebo.utils.AsyncImageLoader;
import com.mm.mealapp.activity.AppConstant;
import com.mm.mealapp.activity.R;
import com.mm.mealapp.api.orders;

public class OrdersAdapter extends BaseAdapter {
	private List<orders> list = null;
	private final Context context;
	private LayoutInflater infater = null;
	private final AsyncImageLoader asyncImageLoader;
	private final String serverUrl;

	public OrdersAdapter(Context context, List<orders> list) {
		this.infater = LayoutInflater.from(context);
		this.list = list;
		this.context = context;
		asyncImageLoader = new AsyncImageLoader(BitmapFactory.decodeResource(context.getResources(),
				R.drawable.pc_loading_fali));
		serverUrl = AppConstant.getRootUrl(context);
	}

	@Override
	public int getCount() {

		return list.size();
	}

	@Override
	public Object getItem(int position) {

		return null;
	}

	@Override
	public long getItemId(int position) {

		return 0;
	}

	@Override
	public View getView(final int position, View convertview, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertview == null) {
			holder = new ViewHolder();
			convertview = infater.inflate(R.layout.listview_item_dishes, null);
			holder.imageView1 = (ImageView) convertview.findViewById(R.id.imageView1);
			holder.tvTitle = (TextView) convertview.findViewById(R.id.tvTitle);
			holder.tvPrice = (TextView) convertview.findViewById(R.id.tvPrice);
			holder.tvIntro = (TextView) convertview.findViewById(R.id.tvIntro);

			convertview.setTag(holder);
		} else {
			holder = (ViewHolder) convertview.getTag();
		}
		holder.tvTitle.setText("�µ�ʱ��" + list.get(position).getCreatetime());
		holder.tvPrice.setText("�ܶ�:" + list.get(position).getPrice()+"\n���ͷ�ʽ:"+list.get(position).getDeliveryway()+"\n����ʱ��:"+list.get(position).getDeliverytime()+"\n֧����ʽ:"+list.get(position).getPayway()+"\n��ע:"+list.get(position).getNode());
		String intro = "";
		if (1 == list.get(position).getStatus()) {
			intro += "����״̬:�Ѿ����";
		} else if (0 == list.get(position).getStatus()) {
			intro += "����״̬:������";
		} else {
			intro += "����״̬:��ȡ��";
		}
		holder.tvIntro.setText(intro);
		asyncImageLoader.loadBitmap(serverUrl + "UploadFile/" + list.get(position).getImg_url(), holder.imageView1);

		return convertview;
	}

	class ViewHolder {
		private ImageView imageView1;
		private TextView tvTitle;
		private TextView tvPrice;
		private TextView tvIntro;
	}

}
