package com.yongweizhang.bilibili.fragment;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.yongweizhang.bilibili.R;
import com.yongweizhang.bilibili.adapter.LiveAdapter;
import com.yongweizhang.bilibili.bean.HomeBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by 张永卫on 2017/3/20.
 */

public class LiveFragment extends BaseFragment {


    @InjectView(R.id.rv_home)
    RecyclerView rvHome;
    @InjectView(R.id.mySwipeRefreshLayout)
    SwipeRefreshLayout mySwipeRefreshLayout;


    private LiveAdapter adapter;


    @Override
    public View initView() {

        View view = View.inflate(getActivity(), R.layout.fragment_live, null);
        ButterKnife.inject(this, view);

        initRefch();

        return view;
    }

    private void initRefch() {
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

    private void getDataFromNet() {

        OkHttpUtils.get()
                .url("http://live.bilibili.com/AppNewIndex/common?_device=android&appkey=\n" +
                        "1d8b6e7d45233436&build=501000&mobi_app=android&platform=android&scale=\n" +
                        "hdpi&ts=1490013188000&sign=92541a11ed62841120e786e637b9db3b\n")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "联网失败" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", "联网成功123123123123123" + response);

                        processData(response);

//                        // 通过 setEnabled(false) 禁用下拉刷新
                        mySwipeRefreshLayout.setRefreshing(false);
                    }
                });
    }

    private void processData(String json) {

        HomeBean homeBean = JSON.parseObject(json, HomeBean.class);
//        homeBean.getData().getBanner().get(0).getImg();

        //设置RecyclerView的适配器
        adapter = new LiveAdapter(mContext, homeBean.getData());

        rvHome.setAdapter(adapter);

        GridLayoutManager manager = new GridLayoutManager(mContext, 1);

        rvHome.setLayoutManager(manager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }



}
