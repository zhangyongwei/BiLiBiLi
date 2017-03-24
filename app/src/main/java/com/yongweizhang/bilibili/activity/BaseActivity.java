package com.yongweizhang.bilibili.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * Created by 张永卫on 2017/3/21.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutid());
        ButterKnife.inject(this);
        initData();
        initTitle();
        initListener();
    }

    public abstract void initListener();

    public abstract void initData();

    public abstract void initTitle() ;


    public abstract int getLayoutid();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }

    //弹吐丝
    public void showToast(String context){

        Toast.makeText(this, context, Toast.LENGTH_SHORT).show();
    }
}
