package com.yongweizhang.bilibili.activity;

import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yongweizhang.bilibili.R;

import butterknife.InjectView;

public class SettingActivity extends BaseActivity {


    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.tv_login)
    TextView tvLogin;
    @InjectView(R.id.et_qx)
    EditText etQx;
    @InjectView(R.id.username)
    TextInputLayout username;
    @InjectView(R.id.et_dz)
    EditText etDz;
    @InjectView(R.id.dz)
    TextInputLayout dz;
    @InjectView(R.id.size)
    TextView size;
    @InjectView(R.id.hight)
    TextView hight;
    @InjectView(R.id.et_set)
    EditText etSet;
    @InjectView(R.id.et_offline)
    EditText etOffline;
    @InjectView(R.id.tv_else)
    TextView tvElse;
    @InjectView(R.id.tv_music)
    TextView tvMusic;
    @InjectView(R.id.et_oritation)
    EditText etOritation;
    @InjectView(R.id.activity_setting)
    LinearLayout activitySetting;

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
        return R.layout.activity_setting;
    }


}
