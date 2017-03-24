package com.yongweizhang.bilibili.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yongweizhang.bilibili.R;
import com.yongweizhang.bilibili.bean.PartitionBean;
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
 * Created by 张永卫on 2017/3/24.
 */

public class TypeAdapter extends RecyclerView.Adapter {


    private final Context mContext;
    //    a;
    private final LayoutInflater inflater;
    /**
     * 分类型
     */
    public static final int CHANNEL = 0;

    public static final int BANNER = 1;

    public static final int NORMAL = 2;

    public static final int BODY = 3;

    public static final int ACTIVITY = 4;
    private final List<PartitionBean.DataBean> data;


    public int currentType = CHANNEL;


    public TypeAdapter(Context mContext, List<PartitionBean.DataBean> data) {
        this.mContext = mContext;
        this.data = data;
        inflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getItemViewType(int position) {

        if (position == 0) {

            currentType = CHANNEL;
        } else if (data.get(position - 1).getBanner() != null && "region".equals(data.get(position - 1).getType())) {

            currentType = BANNER;
        } else if (data.get(position - 1).getBanner() == null && "region".equals(data.get(position - 1).getType())) {

            currentType = NORMAL;

        } else if ("topic".equals(data.get(position - 1).getType())) {

            currentType = BODY;
        } else if ("activity".equals(data.get(position - 1).getType())) {

            currentType = ACTIVITY;
        }
        return currentType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == CHANNEL) {

            return new ChannelViewHolder(mContext, inflater.inflate(R.layout.partition_grid, null));
        } else if (viewType == BANNER) {

            return new BannerViewHolder(mContext, inflater.inflate(R.layout.partition_banner, null));
        } else if (viewType == NORMAL) {

            return new NormalViewHolder(mContext, inflater.inflate(R.layout.partition_normal, null));
        } else if (viewType == BODY) {

            return new BodyViewHolder(mContext, inflater.inflate(R.layout.partition_body, null));
        } else if (viewType == ACTIVITY) {

            return new ActivityViewHolder(mContext, inflater.inflate(R.layout.banner_viewpager, null));
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == CHANNEL) {
            ChannelViewHolder viewHolder = (ChannelViewHolder) holder;
            viewHolder.setData();
        } else if (getItemViewType(position) == BANNER) {

            BannerViewHolder viewHolder = (BannerViewHolder) holder;
            viewHolder.setData(data, position);
        } else if (getItemViewType(position) == NORMAL) {

            NormalViewHolder viewHolder = (NormalViewHolder) holder;

            viewHolder.setData(data, position);
        } else if (getItemViewType(position) == BODY) {

            BodyViewHolder viewHolder = (BodyViewHolder) holder;

            viewHolder.setData(data, position);
        }else if(getItemViewType(position)==ACTIVITY){

            ActivityViewHolder viewHolder = (ActivityViewHolder) holder;

            viewHolder.setData(data,position);
        }

    }

    @Override
    public int getItemCount() {
        return data.size() + 1;
    }

    class ActivityViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        @InjectView(R.id.banner)
        Banner banner;


        public ActivityViewHolder(Context mContext, View inflate) {
            super(inflate);
            this.mContext = mContext;
        }


        public void setData(List<PartitionBean.DataBean> data, int position) {


        }
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        @InjectView(R.id.tv_title)
        TextView tvTitle;
        @InjectView(R.id.tv_more)
        TextView tvMore;
        @InjectView(R.id.iv_right)
        ImageView ivRight;
        @InjectView(R.id.iv_view)
        ImageView ivView;

        public BodyViewHolder(Context mContext, View inflate) {
            super(inflate);
            ButterKnife.inject(this, inflate);
            this.mContext = mContext;
        }

        public void setData(List<PartitionBean.DataBean> data, int position) {
            PartitionBean.DataBean dataBean = data.get(position - 1);

            tvTitle.setText(dataBean.getTitle());
            Log.e("TAG", "TypeAdapter" + position);
            Glide.with(mContext).load(dataBean.getBody().get(0).getCover()).into(ivView);
        }
    }

    class NormalViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        @InjectView(R.id.iv_phto)
        ImageView ivPhto;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.btn_num)
        Button btnNum;
        @InjectView(R.id.iv_right)
        ImageView ivRight;
        @InjectView(R.id.gv_normal)
        MyGridView gvNormal;
        @InjectView(R.id.btn_more)
        Button btnMore;
        @InjectView(R.id.tv_refresh)
        TextView tvRefresh;


        public NormalViewHolder(Context mContext, View inflate) {
            super(inflate);
            ButterKnife.inject(this, inflate);
            this.mContext = mContext;
        }

        public void setData(List<PartitionBean.DataBean> data, int position) {

            PartitionBean.DataBean dataBean = data.get(position - 1);

            tvName.setText(dataBean.getTitle());


            //设置适配器
            NormalAdapter adapter = new NormalAdapter(mContext, dataBean);
            gvNormal.setAdapter(adapter);

        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        @InjectView(R.id.iv_phto)
        ImageView ivPhto;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.btn_num)
        Button btnNum;
        @InjectView(R.id.iv_right)
        ImageView ivRight;
        @InjectView(R.id.gv_recommend)
        MyGridView gvRecommend;
        @InjectView(R.id.btn_more)
        Button btnMore;
        @InjectView(R.id.tv_refresh)
        TextView tvRefresh;
        @InjectView(R.id.iv_banner)
        Banner ivBanner;

        public BannerViewHolder(Context mContext, View inflate) {
            super(inflate);
            ButterKnife.inject(this, inflate);
            this.mContext = mContext;
        }

        public void setData(List<PartitionBean.DataBean> data, int position) {

            PartitionBean.DataBean dataBean = data.get(position - 1);
            tvName.setText(dataBean.getTitle());
            BannerAdapter adapter = new BannerAdapter(mContext, dataBean);
            gvRecommend.setAdapter(adapter);

            gvRecommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, "被点击了", Toast.LENGTH_SHORT).show();
                }
            });
            List<String> images = new ArrayList<>();
            List<PartitionBean.DataBean.BannerBean.BottomBean> list = dataBean.getBanner().getBottom();


            for (int i = 0; i < list.size(); i++) {

                images.add(list.get(i).getImage());
            }

            //简单使用
            ivBanner.setImages(images)
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
            ivBanner.setBannerAnimation(BackgroundToForegroundTransformer.class);
            //设置点击事件
            ivBanner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext, "点击", Toast.LENGTH_SHORT).show();
                }
            });


        }
    }

    class ChannelViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;

        @InjectView(R.id.gv_partition)
        MyGridView gvPartition;
        private ChannelAdapter adapter;

        public ChannelViewHolder(Context mContext, View inflate) {
            super(inflate);
            ButterKnife.inject(this, inflate);
            this.mContext = mContext;
        }

        public void setData() {

            //设置适配器
            adapter = new ChannelAdapter(mContext);
            gvPartition.setAdapter(adapter);

            gvPartition.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, "被点击了", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
