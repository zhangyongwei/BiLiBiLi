package com.yongweizhang.bilibili.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yongweizhang.bilibili.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 张永卫on 2017/3/24.
 */

public class ChannelAdapter extends BaseAdapter {


    private final Context mContext;

    int[] list = {R.drawable.ic_head_live, R.drawable.ic_category_t13, R.drawable.ic_category_t1,
            R.drawable.ic_head_live_banggumu, R.drawable.ic_category_t3, R.drawable.ic_category_t129,
            R.drawable.ic_category_t4, R.drawable.ic_category_t36, R.drawable.ic_category_t160,
            R.drawable.ic_category_t119, R.drawable.ic_category_t155, R.drawable.ic_category_t165,
            R.drawable.ic_category_t5, R.drawable.ic_category_t23, R.drawable.ic_category_t11, R.drawable.ic_category_game_center};

    String[] title = {"直播", "番剧", "动画", "国创", "音乐", "舞蹈", "游戏", "科技",
            "生活", "鬼畜", "时尚", "广告", "娱乐", "电影", "电视剧", "游戏中心"};

    public ChannelAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return list.length;
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

            convertView = View.inflate(mContext, R.layout.item_partition, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{

            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.ivDraw.setImageResource(list[position]);
        viewHolder.tvTtt.setText(title[position]);
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_draw)
        ImageView ivDraw;
        @InjectView(R.id.tv_ttt)
        TextView tvTtt;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
