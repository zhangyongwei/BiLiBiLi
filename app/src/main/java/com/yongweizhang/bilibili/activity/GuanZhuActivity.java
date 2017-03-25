package com.yongweizhang.bilibili.activity;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yongweizhang.bilibili.R;

import butterknife.InjectView;

public class GuanZhuActivity extends BaseActivity {


    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.tv_text)
    TextView tvText;
    @InjectView(R.id.activity_guan_zhu)
    LinearLayout activityGuanZhu;

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {


    }

    @Override
    public void initTitle() {

    }

    @Override
    public int getLayoutid() {
        return R.layout.activity_guan_zhu;
    }



}
