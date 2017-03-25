package com.yongweizhang.bilibili.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yongweizhang.bilibili.R;
import com.yongweizhang.bilibili.bean.YuanChauangBean;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 张永卫on 2017/3/25.
 */

public class YuanChuangAdapter extends RecyclerView.Adapter {

    private final List<YuanChauangBean.DataBean> data;
    private final Context mContext;
    private final LayoutInflater inflater;
    public static final int YUAN = 1;
    /**
     * 当前类型
     */
    public int currentType = YUAN;

    public YuanChuangAdapter(Context mContext, List<YuanChauangBean.DataBean> data) {
        this.data = data;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemViewType(int position) {

        if (position == YUAN) {

            currentType = YUAN;
        }
        return currentType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == YUAN) {

            return new YuanViewHolder(mContext, inflater.inflate(R.layout.listview_view, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == YUAN) {

            YuanViewHolder viewHolder = (YuanViewHolder) holder;
            viewHolder.setData(data, position);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class YuanViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        @InjectView(R.id.tv_position)
        TextView tvPosition;
        @InjectView(R.id.ll_item)
        LinearLayout llItem;
        @InjectView(R.id.iv_tup)
        ImageView ivTup;
        @InjectView(R.id.tv_titlee)
        TextView tvTitlee;
        @InjectView(R.id.tv_vip)
        TextView tvVip;
        @InjectView(R.id.tv_zh)
        TextView tvZh;
        @InjectView(R.id.tv_fen)
        TextView tvFen;
        @InjectView(R.id.ll_mm)
        LinearLayout llMm;


        public YuanViewHolder(Context mContext, View inflate) {
            super(inflate);
            ButterKnife.inject(this,inflate);
            this.mContext = mContext;
        }

        public void setData(List<YuanChauangBean.DataBean> data, int position) {

            YuanChauangBean.DataBean daBean = data.get(position);

            tvPosition.setText((position+1)+"");
            tvTitlee.setText(daBean.getTitle());
            tvVip.setText(daBean.getName());
            tvFen.setText(daBean.getPts()+"");
            Glide.with(mContext).load(daBean.getCover()).into(ivTup);
            tvZh.setText("综合评分:");

        }
    }
//    private final Context mContext;
//    private final List<YuanChauangBean.DataBean> data;
//
//    public YuanChuangAdapter(Context mContext, List<YuanChauangBean.DataBean> data) {
//        this.mContext = mContext;
//        this.data = data;
//    }
//
//
//    @Override
//    public int getCount() {
//        return data.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder;
//        if (convertView == null) {
//
//            convertView = View.inflate(mContext, R.layout.listview_view, null);
//
//            viewHolder = new ViewHolder(convertView);
//
//            convertView.setTag(viewHolder);
//
//        } else {
//
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//
//        YuanChauangBean.DataBean dataBean = data.get(position);
//        viewHolder.tvPosition.setText((position + 1) + "");
//        viewHolder.tvTitlee.setText(dataBean.getTitle());
//        Glide.with(mContext).load(dataBean.getCover()).into(viewHolder.ivTup);
//        viewHolder.tvVip.setText(dataBean.getName());
//        viewHolder.tvFen.setText(dataBean.getPts() + "");
//        viewHolder.tvZh.setText("综合评价:");
//
//        return convertView;
//    }
//
//
//    static class ViewHolder {
//        @InjectView(R.id.tv_position)
//        TextView tvPosition;
//        @InjectView(R.id.ll_item)
//        LinearLayout llItem;
//        @InjectView(R.id.iv_tup)
//        ImageView ivTup;
//        @InjectView(R.id.tv_titlee)
//        TextView tvTitlee;
//        @InjectView(R.id.tv_vip)
//        TextView tvVip;
//        @InjectView(R.id.tv_zh)
//        TextView tvZh;
//        @InjectView(R.id.tv_fen)
//        TextView tvFen;
//        @InjectView(R.id.ll_mm)
//        LinearLayout llMm;
//
//        ViewHolder(View view) {
//            ButterKnife.inject(this, view);
//        }
//    }
}
