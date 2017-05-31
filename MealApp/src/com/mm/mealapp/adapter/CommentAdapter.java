package com.mm.mealapp.adapter;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.mm.mealapp.activity.R;
import com.mm.mealapp.api.comments;

public class CommentAdapter extends BaseAdapter {
	private List<comments> list = null;
	private final Context context;
	private LayoutInflater infater = null;

	public CommentAdapter(Context context, List<comments> list) {
		this.infater = LayoutInflater.from(context);
		this.list = list;
		this.context = context;

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
			holder.tvTitle = (TextView) convertview.findViewById(R.id.tvTitle);
			holder.tvIntro = (TextView) convertview.findViewById(R.id.tvIntro);
			holder.tvPrice = (TextView) convertview.findViewById(R.id.tvPrice);
			convertview.findViewById(R.id.imageView1).setVisibility(View.GONE);

			convertview.setTag(holder);
		} else {
			holder = (ViewHolder) convertview.getTag();
		}
		holder.tvIntro.setText("创建时间:" + list.get(position).getCreatetime());
		holder.tvPrice.setText(list.get(position).getBody());
		holder.tvTitle.setText(list.get(position).getUsername());

		return convertview;
	}

	public Context getContext() {
		return context;
	}

	class ViewHolder {

		private TextView tvTitle;
		private TextView tvPrice;
		private TextView tvIntro;

	}

}
