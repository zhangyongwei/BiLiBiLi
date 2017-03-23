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

public class ZongHeAdapter extends RecyclerView.Adapter {


    //用他来加载布局
    private final LayoutInflater inflater;

    private final Context mContext;
    private final List<ZongHeBean.DataBean> datas;

    public static final int GRID = 1;

    private ZongGridAdapter adapter;

    /**
     * 当前类型
     */
    public int currentType = GRID;

    public ZongHeAdapter(Context mContext, List<ZongHeBean.DataBean> data) {
        this.mContext = mContext;
        this.datas = data;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemViewType(int position) {

        if (position == GRID) {

            currentType = GRID;
        }
        return currentType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == GRID) {

            return new GridViewHolder(mContext, inflater.inflate(R.layout.grid_iltem, null));
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        GridViewHolder viewHolder = (GridViewHolder) holder;

        viewHolder.setData(datas);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class GridViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        @InjectView(R.id.gv_card)
        MyGridView gvCard;

        public GridViewHolder(Context mContext, View inflate) {
            super(inflate);
            ButterKnife.inject(this,inflate);
            this.mContext = mContext;
        }

        public void setData(List<ZongHeBean.DataBean> datas) {

            adapter = new ZongGridAdapter(mContext,datas);
            gvCard.setAdapter(adapter);

        }
    }
}
