package com.yongweizhang.bilibili.shopAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.yongweizhang.bilibili.R;
import com.yongweizhang.bilibili.activity.GoodsInfoActivity;
import com.yongweizhang.bilibili.adapter.HotGridViewAdapter;
import com.yongweizhang.bilibili.bean.GoodsBean;
import com.yongweizhang.bilibili.bean.OneBean;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 张永卫on 2017/3/28.
 */

public class OneAdapter extends RecyclerView.Adapter {


    public static final String GOODS_BEAN = "goodsBean";
    private final Context mContext;
    private final OneBean.ResultBean result;
    private final LayoutInflater inflater;


    public static final int RECOMMEND = 1;
    /**
     * 当前类型
     */
    public int currentType = RECOMMEND;


    @Override
    public int getItemViewType(int position) {
        if (position == RECOMMEND) {

            currentType = RECOMMEND;
        }
        return currentType;
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public OneAdapter(Context mContext, OneBean.ResultBean result) {

        this.mContext = mContext;
        this.result = result;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == RECOMMEND) {

            return new RecommendViewHolder(mContext, inflater.inflate(R.layout.recommend_item, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == RECOMMEND) {

            RecommendViewHolder viewHolder = (RecommendViewHolder) holder;

            viewHolder.setData(result.getRecommend_info());
        }

    }

    class RecommendViewHolder extends RecyclerView.ViewHolder {

        private final Context mContent;
        @InjectView(R.id.tv_more_recommend)
        TextView tvMoreRecommend;
        @InjectView(R.id.gv_recommend)
        GridView gvRecommend;

        public RecommendViewHolder(Context mContext, View inflate) {
            super(inflate);
            ButterKnife.inject(this, itemView);
            this.mContent = mContext;
        }

        public void setData(final List<OneBean.ResultBean.RecommendInfoBean> recommend_info) {

            HotGridViewAdapter adapter = new HotGridViewAdapter(mContext, recommend_info);
            gvRecommend.setAdapter(adapter);

            gvRecommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    OneBean.ResultBean.RecommendInfoBean recommendInfoBean = recommend_info.get(position);
                    //传递数据
                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setName(recommendInfoBean.getName());
                    goodsBean.setCover_price(recommendInfoBean.getCover_price());
                    goodsBean.setFigure(recommendInfoBean.getFigure());
                    goodsBean.setProduct_id(recommendInfoBean.getProduct_id());


                    Intent intent = new Intent(mContext, GoodsInfoActivity.class);
                    intent.putExtra(GOODS_BEAN,goodsBean);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
