package com.yongweizhang.bilibili.shopfragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.yongweizhang.bilibili.R;
import com.yongweizhang.bilibili.bean.OneBean;
import com.yongweizhang.bilibili.fragment.BaseFragment;
import com.yongweizhang.bilibili.shopAdapter.OneAdapter;
import com.yongweizhang.bilibili.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by 张永卫on 2017/3/21.
 */

public class OneFragment extends BaseFragment {


    @InjectView(R.id.rv_home)
    RecyclerView rvHome;
    private OneAdapter adapter;
    private static final int REQUEST_CODE = 111;

    @Override
    public View initView() {

        View view = View.inflate(mContext, R.layout.fragment_one, null);
        ButterKnife.inject(this,view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        getDataFromNet();

    }

    private void getDataFromNet() {


        String url = Constants.HOME_URL;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {

                        Log.e("TAG", "联网失败" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String s, int i) {

                        Log.e("TAG", "联网成功" + s);
                        processData(s);
                    }

                });
    }

    private void processData(String s) {
        //使用fastjson解析json数据
        OneBean oneBean = JSON.parseObject(s, OneBean.class);
        Log.e("TAG", "解析数据成功=="+oneBean.getResult().getHot_info().get(0).getName());

        //设置RecyclerView的适配器
        adapter = new OneAdapter(mContext,oneBean.getResult());

        rvHome.setAdapter(adapter);

        GridLayoutManager manager = new GridLayoutManager(mContext,1);


        // rvHome.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        rvHome.setLayoutManager(manager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


}
