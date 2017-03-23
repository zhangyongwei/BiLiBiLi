package com.yongweizhang.bilibili.activity;

import android.content.Intent;
import android.os.Handler;

import com.yongweizhang.bilibili.R;

public class SplashActivity extends BaseActivity {

    private Handler handler = new Handler();
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


        //发送延迟消息
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashActivity.this,MainActivity.class);

                startActivity(intent);
                //销毁当前页面
                finish();
            }
        }, 2000);

        return R.layout.activity_splash;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除消息
        handler.removeCallbacksAndMessages(null);
    }
}
