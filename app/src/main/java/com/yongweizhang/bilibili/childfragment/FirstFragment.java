package com.yongweizhang.bilibili.childfragment;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.yongweizhang.bilibili.R;
import com.yongweizhang.bilibili.adapter.TagAdapter;
import com.yongweizhang.bilibili.bean.TagBean;
import com.yongweizhang.bilibili.fragment.BaseFragment;
import com.yongweizhang.bilibili.utils.AppNetConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by 张永卫on 2017/3/27.
 */

public class FirstFragment extends BaseFragment {

    @InjectView(R.id.rv_first)
    RecyclerView rvFirst;
    @InjectView(R.id.mySwipeRefreshLayout)
    SwipeRefreshLayout mySwipeRefreshLayout;
    private TagAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_first, null);
        ButterKnife.inject(this,view);

        onRefresh();
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        getDataFromNet();
    }
//    String url = "http://app.bilibili.com/x/v2/search?appkey=1d8b6e7d45233436&build=501000&duration=0&keyword=极乐净土&mobi_app=android&platform=android&pn=1&ps=20";
    private void getDataFromNet() {

        OkHttpUtils.get()
                .url(AppNetConfig.SEARCH_FIRST+getActivity().getIntent().getStringExtra("key")+
                AppNetConfig.SEARCH_THIRD)
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

        TagBean tagBean = JSON.parseObject(json, TagBean.class);
        List<TagBean.DataBean.ItemsBean.ArchiveBean> archiveBeen = tagBean.getData().getItems().getArchive();

        //设置适配器
        adapter = new TagAdapter(mContext,archiveBeen);
        rvFirst.setAdapter(adapter);

        GridLayoutManager manager = new GridLayoutManager(mContext, 1);

        rvFirst.setLayoutManager(manager);

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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
