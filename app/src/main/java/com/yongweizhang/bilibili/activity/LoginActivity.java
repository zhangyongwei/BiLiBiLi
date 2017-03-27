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
import com.yongweizhang.bilibili.utils.IEditTextChangeListener;
import com.yongweizhang.bilibili.utils.WorksSizeCheckUtil;

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

        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegistActivity.class);
                startActivity(intent);
            }
        });

        //1.设置监听
        WorksSizeCheckUtil.textChangeListener textChangeListener =
                new WorksSizeCheckUtil.textChangeListener(btnLogin);

        //2.
        textChangeListener.addAllEditText(etUser, etPwd);

        //3.判断是否有内容，如果有内容改变颜色
        WorksSizeCheckUtil.setChangeListener(new IEditTextChangeListener() {
            @Override
            public void textChange(boolean isHasContent) {
                if(isHasContent) {

                    btnLogin.setEnabled(true);

                    btnLogin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent =new Intent(LoginActivity.this,MainActivity.class);

                             startActivity(intent);
                        }
                    });
                }else{

                    btnLogin.setEnabled(false);

                }
            }
        });

    }


    @Override
    public void initData() {

        username.setHint("你的手机号/邮箱");
        passWord.setHint("请输入密码");
//        username.setErrorEnabled(true);
//        passWord.setErrorEnabled(true);
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
