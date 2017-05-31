package com.mm.mealapp.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mm.mealapp.activity.R;

public class StepsGridViewAdapter extends BaseAdapter{

	private ArrayList<String> mNumList = new ArrayList<String>();
	private ArrayList<String> mNameList = new ArrayList<String>();  
    private ArrayList<Drawable> mDrawableList = new ArrayList<Drawable>();  
    private LayoutInflater mInflater;  
    private Context mContext;  
    LinearLayout.LayoutParams params;  
  
    public StepsGridViewAdapter(Context context,ArrayList<String> numList,ArrayList<String> nameList, ArrayList<Drawable> drawableList) {  
    	mNumList = numList;
        mNameList = nameList;  
        mDrawableList = drawableList;  
        mContext = context;  
        mInflater = LayoutInflater.from(context);  
          
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,200,LinearLayout.LayoutParams.WRAP_CONTENT);  
        params.gravity = Gravity.LEFT;  
    }  
  
    public int getCount() {  
        return mNameList.size();  
    }  
  
    public Object getItem(int position) {  
        return mNameList.get(position);  
    } 
  
    public long getItemId(int position) {  
        return position;  
    }  
  
    public View getView(int position, View convertView, ViewGroup parent) {  
        ItemViewTag viewTag;  
          
        if (convertView == null)  
        {  
            convertView = mInflater.inflate(R.layout.prosteps_gridview_item, null);  
              
            // construct an item tag   
            viewTag = new ItemViewTag((TextView) convertView.findViewById(R.id.gv_item_num),(ImageView) convertView.findViewById(R.id.gv_item_img), (TextView) convertView.findViewById(R.id.gv_item_tv));  
            convertView.setTag(viewTag);  
        } else  
        {  
            viewTag = (ItemViewTag) convertView.getTag();  
        }  
        viewTag.mNum.setText(mNumList.get(position));
        // set name   
        viewTag.mName.setText(mNameList.get(position));   
        // set icon   
        viewTag.mIcon.setBackgroundDrawable(mDrawableList.get(position));  
        viewTag.mIcon.setLayoutParams(params); 
        return convertView;  
    }  
      
    class ItemViewTag  
    {  
    	protected TextView mNum;
        protected ImageView mIcon;  
        protected TextView mName;  
          
        /** 
         * The constructor to construct a navigation view tag 
         *  
         * @param name 
         *            the name view of the item 
         * @param size 
         *            the size view of the item 
         * @param icon 
         *            the icon view of the item 
         */  
        public ItemViewTag(TextView num,ImageView icon, TextView name)  
        {  	
        	this.mNum = num;
            this.mName = name;  
            this.mIcon = icon; 
        }  
    }  

}
