package com.mm.mealapp.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.mm.mealapp.activity.R;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
    public String[] group = { "单品", "套餐", "特色" };
    public String[][] gridViewChild = { { "包子", "油条", "面包", "鸡蛋饼", "粥", "豆浆", "混沌", "豆腐脑","小咸菜","茶叶蛋","咸鸡蛋" },
            { "中式", "西式" }, { "宝宝止咳", "美容养颜", "健康低脂"} };
    
    String[][] child = { { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" }, { "" } };
    LayoutInflater mInflater;
    Context context;

    public ExpandableListViewAdapter(Context context) {
        // TODO Auto-generated constructor stub
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return child[groupPosition][childPosition];
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if (convertView == null) {
            mViewChild = new ViewChild();
            convertView = mInflater.inflate(R.layout.channel_expandablelistview_item, null);
            mViewChild.gridView = (GridView) convertView.findViewById(R.id.channel_item_child_gridView);
            convertView.setTag(mViewChild);
        } else {
            mViewChild = (ViewChild) convertView.getTag();
        }

        SimpleAdapter mSimpleAdapter = new SimpleAdapter(context, setGridViewData(gridViewChild[groupPosition]), R.layout.channel_gridview_item,
                new String[] { "channel_gridview_item" }, new int[] { R.id.channel_gridview_item });
        mViewChild.gridView.setAdapter(mSimpleAdapter);
        setGridViewListener(mViewChild.gridView);
        mViewChild.gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));

//        hashMap.put(groupPosition + "", mViewChild.gridView);
        return convertView;
    }

    /**
     * 设置gridview点击事件监听
     * 
     * @param gridView
     */
    private void setGridViewListener(final GridView gridView) {
        gridView.setOnItemClickListener(new GridView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                if(view instanceof TextView){
                    //如果想要获取到哪一行，则自定义gridview的adapter，item设置2个textview一个隐藏设置id，显示哪一行
                    TextView tv = (TextView) view ;
                    Toast.makeText(context, "position=" + position+"||"+tv.getText(), Toast.LENGTH_SHORT).show();
                    Log.e("hefeng", "gridView listaner position=" + position + "||text="+tv.getText());
                }
            }
        });
    }


    /**
     * 设置gridview数据
     * 
     * @param data
     * @return
     */
    private ArrayList<HashMap<String, Object>> setGridViewData(String[] data) {
        ArrayList<HashMap<String, Object>> gridItem = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < data.length; i++) {
            HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("channel_gridview_item", data[i]);
            gridItem.add(hashMap);
        }
        return gridItem;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        // TODO Auto-generated method stub
        return child[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        // TODO Auto-generated method stub
        return group[groupPosition];
    }

    @Override
    public int getGroupCount() {
        // TODO Auto-generated method stub
        return group.length;
    }

    @Override
    public long getGroupId(int groupPosition) {
        // TODO Auto-generated method stub
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if (convertView == null) {
            mViewChild = new ViewChild();
            convertView = mInflater.inflate(R.layout.channel_expandablelistview, null);
            mViewChild.textView = (TextView) convertView.findViewById(R.id.channel_group_name);
            mViewChild.imageView = (ImageView) convertView.findViewById(R.id.channel_imageview_orientation);
            convertView.setTag(mViewChild);
        } else {
            mViewChild = (ViewChild) convertView.getTag();
        }

        if (isExpanded) {
            mViewChild.imageView.setImageResource(R.drawable.detail);
        } else {
            mViewChild.imageView.setImageResource(R.drawable.detail2);
        }
        mViewChild.textView.setText(getGroup(groupPosition).toString());
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return true;
    }

    ViewChild mViewChild;

    static class ViewChild {
        ImageView imageView;
        TextView textView;
        GridView gridView;
    }
}
