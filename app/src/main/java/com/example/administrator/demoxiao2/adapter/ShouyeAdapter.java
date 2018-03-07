package com.example.administrator.demoxiao2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.demoxiao2.R;
import com.example.administrator.demoxiao2.clazz.Shouye;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2018/3/2.
 */

public class ShouyeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Shouye.ResultBean.DataBean> data;
    private Context mContext;

    public ShouyeAdapter(List<Shouye.ResultBean.DataBean> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
    }

    @Override
    public int getItemViewType(int position) {

        int type=-1;
        if(!"".equals(data.get(position).getThumbnail_pic_s03())){
            type=3;
        }else if(!"".equals(data.get(position).getThumbnail_pic_s02())){
            type=2;
        }else {
            type=1;
        }


        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.shouye, parent, false);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(!"".equals(data.get(position).getThumbnail_pic_s03())){
            ((ViewHolder)holder).title.setText(data.get(position).getTitle());
            Picasso.with(mContext).load(data.get(position).getThumbnail_pic_s()).into(((ViewHolder)holder).img);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.txt);
        }
    }
}
