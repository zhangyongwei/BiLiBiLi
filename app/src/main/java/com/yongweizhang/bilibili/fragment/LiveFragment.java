package com.yongweizhang.bilibili.fragment;

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

    private LiveAdapter adapter;

    @Override
    public View initView() {

        View view = View.inflate(getActivity(), R.layout.fragment_live, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet();
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
                        Log.e("TAG", "联网成功" + response);

                        processData(response);
                    }
                });
    }

    private void processData(String json) {

        HomeBean homeBean = JSON.parseObject(json, HomeBean.class);
//        homeBean.getData().getBanner().get(0).getImg();

        //设置RecyclerView的适配器
        adapter = new LiveAdapter(mContext,homeBean.getData());

        rvHome.setAdapter(adapter);

        GridLayoutManager manager = new GridLayoutManager(mContext,1);

        rvHome.setLayoutManager(manager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
