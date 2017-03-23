package com.yongweizhang.bilibili.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yongweizhang.bilibili.R;
import com.yongweizhang.bilibili.activity.LiveInfoActivity;
import com.yongweizhang.bilibili.bean.HomeBean;
import com.yongweizhang.bilibili.view.MyGridView;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.transformer.BackgroundToForegroundTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 张永卫on 2017/3/21.
 */

public class LiveAdapter extends RecyclerView.Adapter {

    //用他来加载布局
    private final LayoutInflater inflater;


    /**
     * 横幅广告
     */
    public static final int BANNER = 0;
    private final Context mContext;
    private final HomeBean.DataBean data;
    /**
     * 频道
     */
    public static final int CHANNEL = 1;
    /**
     * 绘画
     */
    public static final int DRAW = 2;



    /**
     * 根据位置得到对应的类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (position == BANNER) {

            currentType = BANNER;

        } else if (position == CHANNEL) {

            currentType = CHANNEL;

        } else if (position == DRAW) {

            currentType = DRAW;
        }

        return currentType;
    }

    /**
     * 当前类型
     */
    public int currentType = BANNER;

    public LiveAdapter(Context mContext, HomeBean.DataBean data) {
        this.mContext = mContext;
        this.data = data;
        inflater = LayoutInflater.from(mContext);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == BANNER) {
            return new BannerViewHolder(mContext, inflater.inflate(R.layout.banner_viewpager, null));
        } else if (viewType == CHANNEL) {

            return new ChannelViewHolder(mContext, inflater.inflate(R.layout.channel_item, null));

        } else if (viewType == DRAW) {

            return new DrawViewHolder(mContext, inflater.inflate(R.layout.listview_item, null));
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {

            BannerViewHolder viewHolder = (BannerViewHolder) holder;

            viewHolder.setData(data.getBanner(), position);
        } else if (getItemViewType(position) == CHANNEL) {

            ChannelViewHolder viewHolder = (ChannelViewHolder) holder;
            viewHolder.setData();
        } else if (getItemViewType(position) == DRAW) {

            DrawViewHolder viewHolder = (DrawViewHolder) holder;

            viewHolder.setData(data.getPartitions());
        }
    }


    @Override
    public int getItemCount() {
        return 11;
    }


    class DrawViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        @InjectView(R.id.iv_random)
        ImageView ivRandom;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.tv_num)
        TextView tvNum;
        @InjectView(R.id.iv_right)
        ImageView ivRight;
        @InjectView(R.id.gv_recommend)
        MyGridView gvRecommend;
        @InjectView(R.id.btn_more)
        Button btnMore;
        @InjectView(R.id.tv_refresh)
        TextView tvRefresh;

        private GridViewAdapter adapter;

        public DrawViewHolder(Context mContext, View view) {
            super(view);
            ButterKnife.inject(this, view);
            this.mContext = mContext;
        }


        public void setData(List<HomeBean.DataBean.PartitionsBean> partitions_info) {


            tvName.setText(partitions_info.get(getLayoutPosition() - 2).getPartition().getName());

            tvNum.setText(partitions_info.get(getLayoutPosition() - 2).getPartition().getCount() + "");

            tvRefresh.setText(partitions_info.get(getLayoutPosition() - 2).getPartition().getCount() + "条动态，点击刷新！");

            Glide.with(mContext).load(partitions_info.get(getLayoutPosition() - 2).getPartition().getSub_icon().getSrc()).into(ivRandom);


            //设置适配器
            adapter = new GridViewAdapter(mContext, partitions_info.get(getLayoutPosition() - 2).getLives());
            gvRecommend.setAdapter(adapter);
            SpannableStringBuilder builder = new SpannableStringBuilder("当前有" + tvNum.getText().toString() + "个直播");

            ForegroundColorSpan redSpan = new ForegroundColorSpan(Color.RED);

            builder.setSpan(redSpan, 3, tvNum.getText().toString().length() + 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


            tvNum.setText(builder);

            tvRefresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "刷新", Toast.LENGTH_SHORT).show();

                }
            });
            ivRandom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "标题", Toast.LENGTH_SHORT).show();

                }
            });
            ivRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "更多", Toast.LENGTH_SHORT).show();

                }
            });
            btnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "查找更多", Toast.LENGTH_SHORT).show();

                }
            });

        }

    }


    class ChannelViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        @InjectView(R.id.ll_icon)
        ImageView llIcon;
        @InjectView(R.id.iv_anchor)
        LinearLayout ivAnchor;
        @InjectView(R.id.iv_center)
        LinearLayout ivCenter;
        @InjectView(R.id.iv_video)
        LinearLayout ivVideo;
        @InjectView(R.id.iv_search)
        LinearLayout ivSearch;
        @InjectView(R.id.iv_category)
        LinearLayout ivCategory;

        public ChannelViewHolder(Context mContext, View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            this.mContext = mContext;
        }

        public void setData() {
            initListener();
        }

        private void initListener() {

            ivAnchor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "关注", Toast.LENGTH_SHORT).show();
                }
            });
            ivCenter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "中心", Toast.LENGTH_SHORT).show();

                }
            });
            ivVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "视频", Toast.LENGTH_SHORT).show();

                }
            });
            ivSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();

                }
            });
            ivCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "分类", Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        private Banner banner;

        public BannerViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            banner = (Banner) itemView.findViewById(R.id.banner);
        }

        public void setData(final List<HomeBean.DataBean.BannerBean> banner_info, int position) {
            //1.得到数据
            //2.设置Banner的数据
//            final HomeBean.DataBean.BannerBean bannerBean = banner_info.get(0);
            List<String> images = new ArrayList<>();

            for (int i = 0; i < 5; i++) {

                images.add(banner_info.get(0).getImg());

            }

            //简单使用
            banner.setImages(images)
                    .setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {

                            //具体方法内容可以选择，此方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
                            Glide.with(mContext)
                                    .load(path)
                                    .crossFade()
                                    .into(imageView);


                        }
                    })
                    .start();

            //设置样式
            banner.setBannerAnimation(BackgroundToForegroundTransformer.class);
            //设置点击事件
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Intent intent = new Intent(mContext, LiveInfoActivity.class);
                    intent.putExtra("banner", banner_info.get(0));
                    mContext.startActivity(intent);
                }
            });

        }
    }
}