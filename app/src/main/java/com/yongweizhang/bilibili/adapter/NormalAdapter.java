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
import com.yongweizhang.bilibili.bean.PartitionBean;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 张永卫on 2017/3/24.
 */

public class NormalAdapter extends BaseAdapter {

    private final Context mContext;
    private final PartitionBean.DataBean data;
    private final List<PartitionBean.DataBean.BodyBean> body;

    public NormalAdapter(Context mContext, PartitionBean.DataBean dataBean) {
        this.data = dataBean;
        this.mContext = mContext;
        body = data.getBody();
    }


    @Override
    public int getCount() {
        return body.size();
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

            convertView = View.inflate(mContext, R.layout.normal_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{

            viewHolder = (ViewHolder) convertView.getTag();
        }

        PartitionBean.DataBean.BodyBean bodyBean = body.get(position);

        viewHolder.tvContent.setText(bodyBean.getTitle());
        viewHolder.tvDanmu.setText(bodyBean.getDanmaku()+"");
        viewHolder.tvType.setText(bodyBean.getPlay()+"");

        Glide.with(mContext).load(bodyBean.getCover()).into(viewHolder.ivDefault);

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
        @InjectView(R.id.tv_danmu)
        TextView tvDanmu;
        @InjectView(R.id.item_live_layout)
        CardView itemLiveLayout;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
