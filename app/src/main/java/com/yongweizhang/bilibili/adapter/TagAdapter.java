package com.yongweizhang.bilibili.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yongweizhang.bilibili.R;
import com.yongweizhang.bilibili.bean.TagBean;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by 张永卫on 2017/3/27.
 */

public class TagAdapter extends RecyclerView.Adapter {

    //用他来加载布局
    private final LayoutInflater inflater;
    private final Context mContext;
    private final List<TagBean.DataBean.ItemsBean.ArchiveBean> datas;

    public static final int GRID = 1;

    /**
     * 当前类型
     */
    public int currentType = GRID;



    public TagAdapter(Context mContext, List<TagBean.DataBean.ItemsBean.ArchiveBean> archiveBeen) {
        this.mContext = mContext;
        this.datas = archiveBeen;
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

            return new CardViewHolder(mContext, inflater.inflate(R.layout.cardviewiltem, parent, false));
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        CardViewHolder viewHolder = (CardViewHolder) holder;

        viewHolder.setData(datas, position);

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    class CardViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        @InjectView(R.id.iv_cover)
        ImageView ivCover;
        @InjectView(R.id.tv_durtion)
        TextView tvDurtion;
        @InjectView(R.id.item_cardview)
        CardView itemCardview;
        @InjectView(R.id.tv_desc)
        TextView tvDesc;
        @InjectView(R.id.tv_username)
        TextView tvUsername;
        @InjectView(R.id.play_count)
        TextView playCount;
        @InjectView(R.id.danmaku_count)
        TextView danmakuCount;

        public CardViewHolder(Context mContext, View inflate) {
            super(inflate);
            ButterKnife.inject(this, inflate);
            this.mContext = mContext;
        }

        public void setData(List<TagBean.DataBean.ItemsBean.ArchiveBean> datas, int position) {

            TagBean.DataBean.ItemsBean.ArchiveBean archiveBean = datas.get(position);

            Glide.with(mContext).load(archiveBean.getCover()).into(ivCover);
            tvDurtion.setText(archiveBean.getDuration());
            tvDesc.setText(archiveBean.getDesc());
            danmakuCount.setText(archiveBean.getDanmaku() + "");
            playCount.setText(archiveBean.getPlay() + "");
            tvUsername.setText(archiveBean.getAuthor());

            itemCardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "被点击了", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


}
