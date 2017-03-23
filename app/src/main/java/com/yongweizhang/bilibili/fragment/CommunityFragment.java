package com.yongweizhang.bilibili.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.github.hymanme.tagflowlayout.OnTagClickListener;
import com.github.hymanme.tagflowlayout.TagAdapter;
import com.github.hymanme.tagflowlayout.TagFlowLayout;
import com.github.hymanme.tagflowlayout.tags.ColorfulTagView;
import com.github.hymanme.tagflowlayout.tags.DefaultTagView;
import com.yongweizhang.bilibili.R;
import com.yongweizhang.bilibili.bean.FoundBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by 张永卫on 2017/3/20.
 */

public class CommunityFragment extends BaseFragment {


    @InjectView(R.id.tv_search)
    TextView tvSearch;
    @InjectView(R.id.tag_flow_layout)
    TagFlowLayout tagFlowLayout;
    @InjectView(R.id.ll_like)
    LinearLayout llLike;
    @InjectView(R.id.ll_center)
    LinearLayout llCenter;
    @InjectView(R.id.ll_activity)
    LinearLayout llActivity;
    @InjectView(R.id.ll_balckhome)
    LinearLayout llBalckhome;
    @InjectView(R.id.ll_rank)
    LinearLayout llRank;
    @InjectView(R.id.ll_all)
    LinearLayout llAll;
    @InjectView(R.id.ll_game)
    LinearLayout llGame;
    @InjectView(R.id.ll_shop)
    LinearLayout llShop;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.community_fragment, null);
        ButterKnife.inject(this, view);
        return view;

    }

    @Override
    public void initData() {
        super.initData();
        initListener();
        getDataFromNet();
    }

    String url = "http://app.bilibili.com/x/v2/search/hot?appkey=1d8b6e7d45233436&build=501000&limit=50&mobi_app=android&platform=android&ts=1490014710000&sign=e5ddf94fa9a0d6876cb85756c37c4adc";

    private void getDataFromNet() {
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "联网失败了" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", "TagFragment的数据联网成功了==" + response);

                        processData(response);
                    }
                });

    }

    private void initListener() {

        tagFlowLayout.setTitle("大家都不想搜");
        tagFlowLayout.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        tagFlowLayout.setTitleTextSize(12);
        //最小显示高度(单位dp)
        tagFlowLayout.setMinVisibleHeight(100);
        //最大显示高度(单位dp)
        tagFlowLayout.setMaxVisibleHeight(400);
        tagFlowLayout.setAnimationDuration(600);
        //设置背景颜色
        tagFlowLayout.setBackGroundColor(getResources().getColor(R.color.primary_text));

        tagFlowLayout.setTagListener(new OnTagClickListener() {
            @Override
            public void onClick(TagFlowLayout parent, View view, int position) {
                Toast.makeText(mContext, "click==" + ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(TagFlowLayout parent, View view, int position) {
                Toast.makeText(mContext, "long click==" + ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void processData(String json) {

        FoundBean foundBean = JSON.parseObject(json, FoundBean.class);
        FoundBean.DataBean dataBean = foundBean.getData();
        List<FoundBean.DataBean.ListBean> list = dataBean.getList();
        //设置adapter
        MyTagAdapter tagAdapter = new MyTagAdapter();
        tagFlowLayout.setTagAdapter(tagAdapter);
        //给adapter绑定数据
        tagAdapter.addAllTags(list);

    }


    @OnClick({R.id.tv_search, R.id.ll_like, R.id.ll_center, R.id.ll_activity, R.id.ll_balckhome, R.id.ll_rank, R.id.ll_all, R.id.ll_game, R.id.ll_shop})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_search:
                Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_like:
                Toast.makeText(mContext, "爱好", Toast.LENGTH_SHORT).show();

                break;
            case R.id.ll_center:
                Toast.makeText(mContext, "中心", Toast.LENGTH_SHORT).show();

                break;
            case R.id.ll_activity:
                Toast.makeText(mContext, "活动", Toast.LENGTH_SHORT).show();

                break;
            case R.id.ll_balckhome:
                Toast.makeText(mContext, "小黑屋", Toast.LENGTH_SHORT).show();

                break;
            case R.id.ll_rank:
                Toast.makeText(mContext, "原创", Toast.LENGTH_SHORT).show();

                break;
            case R.id.ll_all:
                Toast.makeText(mContext, "全部", Toast.LENGTH_SHORT).show();

                break;
            case R.id.ll_game:
                Toast.makeText(mContext, "游戏", Toast.LENGTH_SHORT).show();

                break;
            case R.id.ll_shop:
                Toast.makeText(mContext, "商城", Toast.LENGTH_SHORT).show();

                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    class MyTagAdapter extends TagAdapter<FoundBean.DataBean.ListBean> {

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //定制tag的样式，包括背景颜色，点击时背景颜色，背景形状等
            DefaultTagView textView = new ColorfulTagView(mContext);
            textView.setText(((FoundBean.DataBean.ListBean) getItem(position)).getKeyword());
            return textView;
        }
    }
}
