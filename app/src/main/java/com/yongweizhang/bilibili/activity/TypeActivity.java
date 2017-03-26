package com.yongweizhang.bilibili.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yongweizhang.bilibili.R;

import butterknife.InjectView;

public class TypeActivity extends BaseActivity {

    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.tv_login)
    TextView tvLogin;
    @InjectView(R.id.activity_type)
    LinearLayout activityType;


    @Override
    public void initListener() {

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initTitle() {

    }

    @Override
    public int getLayoutid() {
        return R.layout.activity_type;
    }
}
