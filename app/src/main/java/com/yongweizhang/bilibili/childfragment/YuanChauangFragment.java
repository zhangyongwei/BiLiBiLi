package com.yongweizhang.bilibili.childfragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.yongweizhang.bilibili.R;
import com.yongweizhang.bilibili.adapter.YuanChuangAdapter;
import com.yongweizhang.bilibili.bean.YuanChauangBean;
import com.yongweizhang.bilibili.fragment.BaseFragment;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by 张永卫on 2017/3/21.
 */

public class YuanChauangFragment extends BaseFragment {


    @InjectView(R.id.rv_yuan)
    RecyclerView rvYuan;

    @Override
    public View initView() {

        View view = View.inflate(mContext, R.layout.fragment_yuanch, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        getDataFromNet();
    }

    String url = "http://app.bilibili.com/x/v2/rank?appkey=1d8b6e7d45233436&build=501000&mobi_app=android&order=origin&platform=android&pn=1&ps=20&ts=1490015891000&sign=1a5a1c73e3b23be37fb13ee0178ceef0";

    private void getDataFromNet() {

        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                        Log.e("TAG", "error=" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {

                        Log.e("TAG", "response=" + response);

                        processData(response);
                    }
                });
    }

    private void processData(String json) {

        YuanChauangBean yuanChauangBean = JSON.parseObject(json, YuanChauangBean.class);

        List<YuanChauangBean.DataBean> data = yuanChauangBean.getData();
        //设置适配器
        YuanChuangAdapter adapter = new YuanChuangAdapter(mContext, data);

        rvYuan.setAdapter(adapter);


        GridLayoutManager manager = new GridLayoutManager(mContext,1);

        rvYuan.setLayoutManager(manager);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }
}
