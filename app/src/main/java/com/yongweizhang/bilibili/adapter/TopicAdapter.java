package com.yongweizhang.bilibili.adapter;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yongweizhang.bilibili.R;
import com.yongweizhang.bilibili.activity.TopicActivity;
import com.yongweizhang.bilibili.activity.TopicWebActivity;
import com.yongweizhang.bilibili.bean.TopicBean;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 张永卫on 2017/3/24.
 */

public class TopicAdapter extends BaseAdapter {


    public static final String KEY = "key";
    public static final String VALUE = "value";
    private final TopicActivity mContext;
    private final TopicBean data;
    private final List<TopicBean.ListBean> list;

    public TopicAdapter(TopicActivity topicActivity, TopicBean topicBean) {
        this.mContext = topicActivity;
        this.data = topicBean;
        list = data.getList();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {

            convertView = View.inflate(mContext, R.layout.topic_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {

            viewHolder = (ViewHolder) convertView.getTag();
        }

        final TopicBean.ListBean listBean = list.get(position);

        viewHolder.tvNumber.setText(listBean.getTitle());

        Glide.with(mContext).load(listBean.getCover()).into(viewHolder.lvItem);
        viewHolder.llIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = listBean.getTitle();
                String link = listBean.getLink();
                Intent intent = new Intent(mContext, TopicWebActivity.class);
                intent.putExtra(KEY,title);
                intent.putExtra(VALUE,link);
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }



    class ViewHolder {
        @InjectView(R.id.lv_item)
        ImageView lvItem;
        @InjectView(R.id.tv_number)
        TextView tvNumber;
        @InjectView(R.id.ll_icon)
        LinearLayout llIcon;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
