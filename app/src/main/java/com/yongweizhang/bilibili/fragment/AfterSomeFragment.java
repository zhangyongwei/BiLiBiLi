package com.yongweizhang.bilibili.fragment;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.yongweizhang.bilibili.R;
import com.yongweizhang.bilibili.adapter.AfterAdapter;
import com.yongweizhang.bilibili.bean.AfterBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by 张永卫on 2017/3/20.
 */

public class AfterSomeFragment extends BaseFragment {


    @InjectView(R.id.rv_after)
    RecyclerView rvAfter;
    @InjectView(R.id.mySwipeRefreshLayout)
    SwipeRefreshLayout mySwipeRefreshLayout;
    private AfterAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_after, null);
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

    @Override
    public void initData() {
        super.initData();

        getDataFromNet();
    }

    String url = "http://bangumi.bilibili.com/api/app_index_page_v4?build=3940&device=phone&mobi_app=iphone&platform=ios";


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

    private void processData(String json) {

        AfterBean afterBean = JSON.parseObject(json, AfterBean.class);

        //设置适配器
        adapter = new AfterAdapter(mContext, afterBean.getResult());
        rvAfter.setAdapter(adapter);


        GridLayoutManager manager = new GridLayoutManager(mContext, 1);

        rvAfter.setLayoutManager(manager);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }



}
