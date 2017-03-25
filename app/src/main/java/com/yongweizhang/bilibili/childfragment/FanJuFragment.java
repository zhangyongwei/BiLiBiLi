package com.yongweizhang.bilibili.childfragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

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

public class FanJuFragment extends BaseFragment {
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
    String url="http://app.bilibili.com/x/v2/rank?appkey=1d8b6e7d45233436&build=501000&mobi_app=android&order=bangumi&platform=android&pn=1&ps=20&ts=1490015891000&sign=c29299ef4b95c26e104efc13437cf628";
    private void getDataFromNet() {

        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                        Log.e("TAG", "error="+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {

                        Log.e("TAG", "response="+response);

                        processData(response);
                    }
                });
    }

    private void processData(String json) {

        YuanChauangBean yuanChauangBean = JSON.parseObject(json, YuanChauangBean.class);

        List<YuanChauangBean.DataBean> data = yuanChauangBean.getData();
        //设置适配器
        YuanChuangAdapter adapter = new YuanChuangAdapter(mContext,data);

        rvYuan.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(mContext,1);

        rvYuan.setLayoutManager(manager);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
