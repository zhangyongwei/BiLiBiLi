package com.yongweizhang.bilibili.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yongweizhang.bilibili.R;
import com.yongweizhang.bilibili.activity.DanmkuVideoActivity;
import com.yongweizhang.bilibili.bean.HomeBean;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 张永卫on 2017/3/22.
 */
public class GridViewAdapter extends BaseAdapter {


    public static final String VIDEO = "video";
    public static final String VT ="vt";
    private final Context mContext;
    private final List<HomeBean.DataBean.PartitionsBean.LivesBean> datas;

    public GridViewAdapter(Context mContext, List<HomeBean.DataBean.PartitionsBean.LivesBean> lives) {

        this.mContext = mContext;
        this.datas = lives;
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
        } else {

            viewHolder = (ViewHolder) convertView.getTag();

        }

        HomeBean.DataBean.PartitionsBean.LivesBean livesBean = datas.get(position);

        viewHolder.tvShuo.setText(livesBean.getTitle());
        viewHolder.tvAuthor.setText(livesBean.getOwner().getName());
        viewHolder.tvAmout.setText(livesBean.getOnline()+ "");
        Glide.with(mContext).load(livesBean.getCover().getSrc()).into(viewHolder.ivRecommend);

        final String playurl = livesBean.getPlayurl();
        final String title = livesBean.getTitle();
        viewHolder.itemLiveLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext, "被点击了", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext, DanmkuVideoActivity.class);
                intent.putExtra(VIDEO, playurl);
                intent.putExtra(VT, title);
                mContext.startActivity(intent);
            }
        });

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
        @InjectView(R.id.item_live_layout)
        CardView itemLiveLayout;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
