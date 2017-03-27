package com.yongweizhang.bilibili.activity;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yongweizhang.bilibili.R;

import butterknife.InjectView;

public class HistoryActivity extends BaseActivity {


    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.tv_login)
    TextView tvLogin;
    @InjectView(R.id.tv_pwd)
    TextView tvPwd;
    @InjectView(R.id.tv_ssss)
    TextView tvSsss;
    @InjectView(R.id.activity_history)
    LinearLayout activityHistory;

    @Override
    public void initListener() {

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DrawerLayout drawer = (DrawerLayout)HistoryActivity.this.findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.START);
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
        return R.layout.activity_history;
    }


}
