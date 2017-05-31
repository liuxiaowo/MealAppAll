package com.mm.mealapp.adapter;

import java.util.ArrayList;
import java.util.List;

import com.mm.mealapp.activity.R;
import com.mm.mealapp.api.address;
import com.mm.mealapp.api.comments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AddressListViewAdapter extends BaseAdapter{

	 	/*private ArrayList<String> namelist = new ArrayList<String>(); 
	 	private ArrayList<String> phonelist = new ArrayList<String>(); 
	 	private ArrayList<String> addresslist = new ArrayList<String>(); */
		private List<address> list = null;
	    private LayoutInflater layoutInflater;  
	    private Context context;  
	    public static int flag = 0;
	    public AddressListViewAdapter(Context context,List<address> list){  
	        this.setContext(context);  
	        this.list= list;
	        this.layoutInflater=LayoutInflater.from(context);  
	    }  
	    /** 
	     * 组件集合，对应list.xml中的控件 
	     * @author Administrator 
	     */  
	    public final class Zujian{  
	        public TextView name;  
	        public TextView phone; 
	        public TextView address; 
	    }  
	    @Override  
	    public int getCount() {  
	        return list.size();  
	    }  
	    /** 
	     * 获得某一位置的数据 
	     */  
	    @Override  
	    public Object getItem(int position) {  
	        return list.get(position);  
	    }  
	    /** 
	     * 获得唯一标识 
	     */  
	    @Override  
	    public long getItemId(int position) {  
	        return position;  
	    }  
	  
	    @Override  
	    public View getView(int position, View convertView, ViewGroup parent) {  
	        Zujian zujian=null;  
	        if(convertView==null){  
	            zujian=new Zujian();  
	            //获得组件，实例化组件  
	            convertView=layoutInflater.inflate(R.layout.address_listview_item, null);  
	            LinearLayout l = (LinearLayout)convertView.findViewById(R.id.address_listview_item);
	            l.setMinimumHeight(150);
	            LinearLayout rl = (LinearLayout)convertView.findViewById(R.id.address_linear4);
	            if(flag%2==0){
	            	rl.setVisibility(View.VISIBLE);
	            }else{
	            	rl.setVisibility(View.INVISIBLE);
	            }
	            zujian.name=(TextView)convertView.findViewById(R.id.address_name);  
	            zujian.phone=(TextView)convertView.findViewById(R.id.address_phone);
	            zujian.address = (TextView)convertView.findViewById(R.id.address_address);
	            convertView.setTag(zujian);  
	        }else{  
	            zujian=(Zujian)convertView.getTag();  
	        }  
	        //绑定数据  
	        zujian.name.setText(list.get(position).getRealname()+list.get(position).getSex());  
	        zujian.phone.setText(list.get(position).getPhone()); 
	        zujian.address.setText(list.get(position).getAddress());
	        return convertView;  
	    }
		public Context getContext() {
			return context;
		}
		public void setContext(Context context) {
			this.context = context;
		}  
	  

}
