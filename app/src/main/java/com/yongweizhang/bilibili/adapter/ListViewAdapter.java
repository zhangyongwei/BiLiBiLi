package com.yongweizhang.bilibili.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.yongweizhang.bilibili.bean.HomeBean;

import java.util.List;

/**
 * Created by 张永卫on 2017/3/22.
 */

public class ListViewAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<HomeBean.DataBean.PartitionsBean> datas;
    private GridViewAdapter adapter;

    public ListViewAdapter(Context mContext, List<HomeBean.DataBean.PartitionsBean> partitions_info) {
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
//        ViewHolder viewHolder;
//        if (convertView == null) {
//
//            convertView = View.inflate(mContext, R.layout.listview_item, null);
//            viewHolder = new ViewHolder(convertView);
//            convertView.setTag(viewHolder);
//
//        }else{
//
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//
//        HomeBean.DataBean.PartitionsBean partitionsBean = datas.get(position);
////        adapter = new GridViewAdapter(mContext,partitionsBean);
//        viewHolder.gvRecommend.setAdapter(adapter);
//        viewHolder.tvName.setText(partitionsBean.getPartition().getName());
//        viewHolder.tvNum.setText(partitionsBean.getPartition().getCount()+"");
//        Glide.with(mContext).load(partitionsBean.getPartition().getSub_icon().getSrc()).into(viewHolder.ivRandom);

        return convertView;
    }

//    static class ViewHolder {
//        @InjectView(R.id.iv_random)
//        ImageView ivRandom;
//        @InjectView(R.id.tv_name)
//        TextView tvName;
//        @InjectView(R.id.tv_num)
//        TextView tvNum;
//        @InjectView(R.id.gv_recommend)
//        MyGridView gvRecommend;
//        @InjectView(R.id.btn_more)
//        Button btnMore;
//        @InjectView(R.id.tv_refresh)
//        TextView tvRefresh;
//
//        ViewHolder(View view) {
//            ButterKnife.inject(this, view);
//        }
//    }
}
