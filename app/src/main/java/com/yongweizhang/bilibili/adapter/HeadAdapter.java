package com.yongweizhang.bilibili.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yongweizhang.bilibili.R;
import com.yongweizhang.bilibili.bean.AfterBean;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 张永卫on 2017/3/23.
 */

public class HeadAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<AfterBean.ResultBean.AdBean.HeadBean> data;

    public HeadAdapter(Context mContext, List<AfterBean.ResultBean.AdBean.HeadBean> head) {

        this.mContext = mContext;
        this.data = head;
    }

    @Override
    public int getCount() {
        return 3;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {

            convertView = View.inflate(mContext, R.layout.head_item, null);

            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        }else{

            viewHolder = (ViewHolder) convertView.getTag();
        }

        AfterBean.ResultBean.AdBean.HeadBean headBean = data.get(position);

        viewHolder.tvContent.setText(headBean.getTitle());

        Glide.with(mContext).load(headBean.getImg()).into(viewHolder.ivDefault);

        viewHolder.itemLiveLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "被点击了", Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_default)
        ImageView ivDefault;
        @InjectView(R.id.tv_content)
        TextView tvContent;
        @InjectView(R.id.tv_type)
        TextView tvType;
        @InjectView(R.id.item_live_layout)
        CardView itemLiveLayout;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
