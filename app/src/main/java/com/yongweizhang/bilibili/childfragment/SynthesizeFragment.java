package com.yongweizhang.bilibili.childfragment;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.yongweizhang.bilibili.R;
import com.yongweizhang.bilibili.adapter.ZongHeAdapter;
import com.yongweizhang.bilibili.bean.ZongHeBean;
import com.yongweizhang.bilibili.fragment.BaseFragment;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by 张永卫on 2017/3/23.
 */

public class SynthesizeFragment extends BaseFragment {


    @InjectView(R.id.rv_zh)
    RecyclerView rvZh;
    @InjectView(R.id.mySwipeRefreshLayout)
    SwipeRefreshLayout mySwipeRefreshLayout;

    private ZongHeAdapter adapter;

    @Override
    public View initView() {

        View view = View.inflate(mContext, R.layout.fragment_zh, null);
        ButterKnife.inject(this, view);

        onRefresh();
        return view;
    }

    private void onRefresh() {
        //设置下拉出现小圆圈是否是缩放出现，出现的位置，最大的下拉位置
        mySwipeRefreshLayout.setProgressViewOffset(true, 50, 200);

        //设置下拉圆圈的大小，两个值 LARGE， DEFAULT
        mySwipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);

        // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
        mySwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_red_light);

        initListener();
    }

    @Override
    public void initData() {
        super.initData();

        getDataFromNet();
    }

    String url = "http://app.bilibili.com/x/feed/index?appkey=1d8b6e7d45233436&build=501000&idx=1490013261&mobi_app=android&network=wifi&platform=android&pull=true&style=2&ts=1490015599000&sign=af4edc66aef7e443c98c28de2b660aa4";

    private void getDataFromNet() {

        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "联网失败" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", "联网成功" + response);

                        processData(response);

                        mySwipeRefreshLayout.setRefreshing(false);
                    }
                });
    }
    private void initListener() {
        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // 刷新动画开始后回调到此方法
                                getDataFromNet();
                            }
                        }, 2000);

                    }
                }
        );
    }
    private void processData(String json) {

        ZongHeBean zongHeBean = JSON.parseObject(json, ZongHeBean.class);

        //设置RecyclerView的适配器
        adapter = new ZongHeAdapter(mContext, zongHeBean.getData());

        rvZh.setAdapter(adapter);

        GridLayoutManager manager = new GridLayoutManager(mContext, 1);

        rvZh.setLayoutManager(manager);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


}
