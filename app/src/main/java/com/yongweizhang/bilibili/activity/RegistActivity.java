package com.yongweizhang.bilibili.activity;

import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yongweizhang.bilibili.R;

import butterknife.InjectView;

public class RegistActivity extends BaseActivity {


    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.tv_login)
    TextView tvLogin;
    @InjectView(R.id.et_where)
    EditText etWhere;
    @InjectView(R.id.tl_local)
    TextInputLayout tlLocal;
    @InjectView(R.id.et_call)
    EditText etCall;
    @InjectView(R.id.tl_phone)
    TextInputLayout tlPhone;
    @InjectView(R.id.btn_password)
    Button btnPassword;
    @InjectView(R.id.activity_regist)
    LinearLayout activityRegist;

    @Override
    public void initListener() {

        btnPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegistActivity.this, "获取验证码", Toast.LENGTH_SHORT).show();
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    public void initData() {

        tlLocal.setHint("香港特别行政区");
        tlPhone.setHint("+110 请输入常用手机号");
    }

    @Override
    public void initTitle() {

    }

    @Override
    public int getLayoutid() {
        return R.layout.activity_regist;
    }


}
