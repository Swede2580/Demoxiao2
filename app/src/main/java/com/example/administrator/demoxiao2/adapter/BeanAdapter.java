package com.example.administrator.demoxiao2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.demoxiao2.R;
import com.example.administrator.demoxiao2.clazz.Bean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2018/3/2.
 */

public class BeanAdapter extends BaseAdapter {
    private List<Bean> beanList;
    private Context mContext;

    public BeanAdapter(List<Bean> beanList, Context mContext) {
        this.beanList = beanList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return beanList.size();
    }

    @Override
    public Object getItem(int position) {
        return beanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh=null;
        if(convertView==null){
            vh=new ViewHolder();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.iten,null);
            vh.tab_imageview= convertView.findViewById(R.id.tab_imageview);
            vh.tab_textview= convertView.findViewById(R.id.tab_textview);

            convertView.setTag(vh);
        }else {
            vh= (ViewHolder) convertView.getTag();
        }

        vh.tab_textview.setText(beanList.get(position).getMane());
        vh.tab_imageview.setImageResource(beanList.get(position).getImg());


        return convertView;


    }

    class ViewHolder {
        ImageView tab_imageview;
        TextView tab_textview;
    }
}
