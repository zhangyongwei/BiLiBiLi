package com.yongweizhang.bilibili.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yongweizhang.bilibili.R;
import com.yongweizhang.bilibili.bean.ZongHeBean;
import com.yongweizhang.bilibili.view.MyGridView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 张永卫on 2017/3/23.
 */

public class DongTaiAdapter extends RecyclerView.Adapter {
    //用他来加载布局
    private final LayoutInflater inflater;

    private final Context mContext;
    private final List<ZongHeBean.DataBean> datas;

    public static final int WINER = 1;

    private DongTaiAdapter adapter;

    /**
     * 当前类型
     */
    public int currentType = WINER;

    public DongTaiAdapter(Context mContext, List<ZongHeBean.DataBean> data) {
        this.mContext = mContext;
        this.datas = data;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemViewType(int position) {

        if (position == WINER) {

            currentType = WINER;
        }
        return currentType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == WINER) {

            return new WinerViewHolder(mContext, inflater.inflate(R.layout.grid_dt, null));
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

       WinerViewHolder viewHolder = (WinerViewHolder) holder;

        viewHolder.setData(datas);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class WinerViewHolder extends RecyclerView.ViewHolder {


        private final Context mContext;
        @InjectView(R.id.gv_dt)
        MyGridView gvDt;

        private DongGridAdapter adapter;

        public WinerViewHolder(Context mContext, View inflate) {
            super(inflate);
            ButterKnife.inject(this,inflate);
            this.mContext = mContext;
        }


        public void setData(List<ZongHeBean.DataBean> datas) {
            adapter = new DongGridAdapter(mContext,datas);
            gvDt.setAdapter(adapter);
        }
    }
}
