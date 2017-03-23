package com.yongweizhang.bilibili.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yongweizhang.bilibili.R;
import com.yongweizhang.bilibili.bean.HomeBean;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 张永卫on 2017/3/22.
 */

public class DrawAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<HomeBean.DataBean.PartitionsBean> datas;


    public DrawAdapter(Context mContext, List<HomeBean.DataBean.PartitionsBean> partitions_info) {
        this.mContext = mContext;
        this.datas = partitions_info;

    }



    @Override
    public int getCount() {
        return datas.size();
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

            convertView = View.inflate(mContext, R.layout.gridview_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        }else{


            viewHolder = (ViewHolder) convertView.getTag();
        }


        HomeBean.DataBean.PartitionsBean partitionsBean = datas.get(position);

        viewHolder.tvShuo.setText(partitionsBean.getLives().get(position).getTitle());
        viewHolder.tvAuthor.setText(partitionsBean.getLives().get(position).getOwner().getName());
        viewHolder.tvAmout.setText(partitionsBean.getLives().get(position).getOnline()+"");

        Glide.with(mContext).load(partitionsBean.getLives().get(position).getCover().getSrc()).into(viewHolder.ivRecommend);
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_recommend)
        ImageView ivRecommend;
        @InjectView(R.id.tv_shuo)
        TextView tvShuo;
        @InjectView(R.id.tv_author)
        TextView tvAuthor;
        @InjectView(R.id.tv_amout)
        TextView tvAmout;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
