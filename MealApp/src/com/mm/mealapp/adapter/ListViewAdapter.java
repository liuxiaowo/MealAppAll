package com.mm.mealapp.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mm.mealapp.activity.R;

public class ListViewAdapter extends BaseAdapter{

	public ArrayList<HashMap<String,String>> list;  
    Activity activity;
    
      
    public ListViewAdapter(Activity activity, ArrayList<HashMap<String,String>> list) {  
        super();  
        this.activity = activity;
        this.list = list;  
    }  
  
      
    public int getCount() {  
        // TODO Auto-generated method stub  
        return list.size();  
    }  
  
      
    public Object getItem(int position) {  
        // TODO Auto-generated method stub  
        return list.get(position);  
    }  
  
  
    public long getItemId(int position) {  
        // TODO Auto-generated method stub  
        return 0;  
    }  
  
    private class ViewHolder {  
           TextView txtFirst;  
           TextView txtSecond;  
      }  
  
        
  
    public View getView(int position, View convertView, ViewGroup parent) {  
        // TODO Auto-generated method stub  
          
        // TODO Auto-generated method stub  
                ViewHolder holder;  
                LayoutInflater inflater =  activity.getLayoutInflater();  
  
                if (convertView == null)  
                {  
                    convertView = inflater.inflate(R.layout.addmaterital_listview_item, null);  
                    holder = new ViewHolder();  
                    holder.txtFirst = (TextView) convertView.findViewById(R.id.material_tv);  
                    holder.txtSecond = (TextView) convertView.findViewById(R.id.useLevel_tv);  
                    convertView.setTag(holder);  
                }  
                else  
                {  
                    holder = (ViewHolder) convertView.getTag();  
                }  
  
                HashMap<String, String> map = list.get(position);  
                holder.txtFirst.setText(map.get("materital"));  
                holder.txtSecond.setText(map.get("userLevel"));  
            return convertView;  
    }  

}
