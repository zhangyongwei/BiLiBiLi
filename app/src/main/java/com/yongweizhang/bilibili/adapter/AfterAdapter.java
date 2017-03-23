package com.yongweizhang.bilibili.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yongweizhang.bilibili.R;
import com.yongweizhang.bilibili.bean.AfterBean;
import com.yongweizhang.bilibili.view.MyGridView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 张永卫on 2017/3/23.
 */

public class AfterAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private final AfterBean.ResultBean result;
    //用他来加载布局
    private final LayoutInflater inflater;

    public static final int GRIDVIEW = 1;

    public static final int CARTON = 2;
    /**
     * 当前类型
     */
    public int currentType = GRIDVIEW;



    public AfterAdapter(Context mContext, AfterBean.ResultBean result) {
        this.mContext = mContext;
        this.result = result;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == GRIDVIEW) {

            currentType = GRIDVIEW;
        } else if (position == CARTON) {

            currentType = CARTON;
        }
        return currentType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == GRIDVIEW) {

            return new GridviewViewHolder(mContext, inflater.inflate(R.layout.after_grid, null));

        } else if (viewType == CARTON) {

            return new CartonViewHolder(mContext, inflater.inflate(R.layout.carton_grid, null));
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == GRIDVIEW) {

            GridviewViewHolder viewHolder = (GridviewViewHolder) holder;

            viewHolder.setData(result.getAd().getHead());
        } else if (getItemViewType(position) == CARTON) {

            CartonViewHolder viewHolder = (CartonViewHolder) holder;

            viewHolder.setData(result.getSerializing());
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }


    class CartonViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;

        @InjectView(R.id.iv_random)
        ImageView ivRandom;
        @InjectView(R.id.tv_title)
        TextView tvTitle;
        @InjectView(R.id.tv_more)
        TextView tvMore;
        @InjectView(R.id.iv_right)
        ImageView ivRight;
        @InjectView(R.id.gv_carton)
        MyGridView gvCarton;

        private CartonAdapter adapter;

        public CartonViewHolder(Context mContext, View inflate) {
            super(inflate);
            this.mContext = mContext;
        }

        public void setData(List<AfterBean.ResultBean.SerializingBean> serializing) {
            //设置适配器
            adapter = new CartonAdapter(mContext, serializing);
            gvCarton.setAdapter(adapter);
        }
    }

    class GridviewViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        @InjectView(R.id.iv_jp)
        ImageView ivJp;
        @InjectView(R.id.iv_cn)
        ImageView ivCn;
        @InjectView(R.id.iv_time)
        LinearLayout ivTime;
        @InjectView(R.id.iv_index)
        LinearLayout ivIndex;
        @InjectView(R.id.ll_bg)
        LinearLayout llBg;
        @InjectView(R.id.iv_random)
        ImageView ivRandom;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.tv_num)
        TextView tvNum;
        @InjectView(R.id.iv_right)
        ImageView ivRight;
        @InjectView(R.id.gv_after)
        MyGridView gvAfter;

        private HeadAdapter adapter;

        public GridviewViewHolder(Context mContext, View inflate) {
            super(inflate);
            ButterKnife.inject(this, inflate);
            this.mContext = mContext;
        }

        public void setData(List<AfterBean.ResultBean.AdBean.HeadBean> head) {

            //设置适配器
            adapter = new HeadAdapter(mContext, head);
            gvAfter.setAdapter(adapter);
        }
    }
}
