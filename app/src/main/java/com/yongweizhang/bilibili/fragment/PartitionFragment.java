package com.yongweizhang.bilibili.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.yongweizhang.bilibili.R;
import com.yongweizhang.bilibili.adapter.TypeAdapter;
import com.yongweizhang.bilibili.bean.PartitionBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by 张永卫on 2017/3/20.
 */

public class PartitionFragment extends BaseFragment {


    @InjectView(R.id.rv_partition)
    RecyclerView rvPartition;

    private TypeAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_partition, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        getDataFromNet();
    }

    String url = "http://app.bilibili.com/x/v2/show/region?appkey=1d8b6e7d45233436&build=501000&mobi_app=android&platform=android&ts=1490014674000&sign=93edb7634f38498a60e5c3ad0b8b0974";
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
                    }
                });
    }

    private void processData(String json) {

        PartitionBean partitionBean = JSON.parseObject(json, PartitionBean.class);

        //设置适配器
        adapter = new TypeAdapter(mContext,partitionBean.getData());
        rvPartition.setAdapter(adapter);

        GridLayoutManager manager = new GridLayoutManager(mContext, 1);

        rvPartition.setLayoutManager(manager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
