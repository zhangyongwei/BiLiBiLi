package com.yongweizhang.bilibili.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yongweizhang.bilibili.R;
import com.yongweizhang.bilibili.bean.ZongHeBean;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 张永卫on 2017/3/23.
 */

public class DongGridAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<ZongHeBean.DataBean> data;

    public DongGridAdapter(Context mContext, List<ZongHeBean.DataBean> datas) {
        this.mContext = mContext;
        this.data = datas;
    }

    @Override
    public int getCount() {
        return data.size();
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

            convertView = View.inflate(mContext, R.layout.dongtai_grid, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);

        }else{

            viewHolder = (ViewHolder) convertView.getTag();
        }

        ZongHeBean.DataBean dataBean = data.get(position);

        viewHolder.tvContent.setText(dataBean.getTitle());

        viewHolder.tvType.setText(dataBean.getTname());

        Glide.with(mContext).load(dataBean.getCover()).into(viewHolder.ivDefault);

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_default)
        ImageView ivDefault;
        @InjectView(R.id.tv_content)
        TextView tvContent;
        @InjectView(R.id.tv_type)
        TextView tvType;
        @InjectView(R.id.iv_dian)
        ImageView ivDian;
        @InjectView(R.id.item_card)
        CardView itemCard;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
