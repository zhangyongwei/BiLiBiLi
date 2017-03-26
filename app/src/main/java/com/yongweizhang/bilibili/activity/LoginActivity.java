package com.yongweizhang.bilibili.activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yongweizhang.bilibili.R;

import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {


    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.tv_login)
    TextView tvLogin;
    @InjectView(R.id.tv_pwd)
    TextView tvPwd;
    @InjectView(R.id.iv_left)
    ImageView ivLeft;
    @InjectView(R.id.iv_logo)
    ImageView ivLogo;
    @InjectView(R.id.iv_right)
    ImageView ivRight;
    @InjectView(R.id.et_user)
    EditText etUser;
    @InjectView(R.id.username)
    TextInputLayout username;
    @InjectView(R.id.et_pwd)
    EditText etPwd;
    @InjectView(R.id.passWord)
    TextInputLayout passWord;
    @InjectView(R.id.btn_regist)
    Button btnRegist;
    @InjectView(R.id.btn_login)
    Button btnLogin;
    @InjectView(R.id.activity_login)
    LinearLayout activityLogin;
    private Intent intent;

    @Override
    public void initListener() {


        etUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) {

                    ivLeft.setImageResource(R.drawable.ic_22);
                    ivRight.setImageResource(R.drawable.ic_33);
                } else {

                    ivLeft.setImageResource(R.drawable.ic_22_hide);
                    ivRight.setImageResource(R.drawable.ic_33_hide);

                }
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                login();

                Intent intent =new Intent(LoginActivity.this,MainActivity.class);

                startActivity(intent);
            }
        });
    }

//    private void login(){
//
//        String username = etUser.getText().toString().trim();
//        String password = etPwd.getText().toString().trim();
//
//        if (TextUtils.isEmpty(username)) {
//
//            showToast("账号不能为空");
//
//            return;
//        }
//        if (TextUtils.isEmpty(password)) {
//
//            showToast("密码不能为空");
//
//            return;
//        }
//
//        //去服务器登录
//        Map<String, String> map = new HashMap<String, String>();
//
//        map.put("user", username);
//
//        map.put("password", password);
//
//        OkHttpUtils.get()
//                .url("http://47.93.118.241:8081/P2PInvest/login")
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//
//                    }
//                });
//    }
    @Override
    public void initData() {

        username.setHint("你的手机号/邮箱");
        passWord.setHint("请输入密码");
        username.setErrorEnabled(true);
        passWord.setErrorEnabled(true);
    }

    @Override
    public void initTitle() {

    }

    @Override
    public int getLayoutid() {
        return R.layout.activity_login;
    }




    @OnClick({R.id.iv_back, R.id.btn_regist, R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_regist:

                intent = new Intent(LoginActivity.this,RegistActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);

                break;
        }
    }
}
