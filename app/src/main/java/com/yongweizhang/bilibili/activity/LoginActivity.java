package com.yongweizhang.bilibili.activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yongweizhang.bilibili.R;
import com.yongweizhang.bilibili.gen.User;
import com.yongweizhang.bilibili.gen.UserDao;
import com.yongweizhang.bilibili.utils.IEditTextChangeListener;
import com.yongweizhang.bilibili.utils.WorksSizeCheckUtil;
import com.yongweizhang.bilibili.view.MyApplication;

import java.util.List;

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

    private UserDao userDao;


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

        userDao = MyApplication.getInstances().getDaoSession().getUserDao();
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
                //校验是否存在
                List<User> users = userDao.loadAll();
                String userName = etUser.getText().toString().trim();

                if(users.size()==0) {

                    User user = new User(null, etUser.getText().toString().trim(),
                            etPwd.getText().toString().trim());
                    userDao.insert(user);

                    Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_SHORT).show();

                    return;
                }else{

                    for (int i = 0; i < users.size(); i++) {

                        if(userName.equals(users.get(i).getUserName().toString())) {

                            Toast.makeText(LoginActivity.this, "该账号已经存在", Toast.LENGTH_SHORT).show();

                            return;
                        }

                    }

                    User user = new User(null, etUser.getText().toString().trim(),
                            etPwd.getText().toString().trim());
                    userDao.insert(user);

                    Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.btn_login:
                //校验是否存在 以及密码是否正确
                String username = etUser.getText().toString().trim();
                String password = etPwd.getText().toString().trim();

                List<User> user = userDao.loadAll();
                for (User list :  user) {

                    String username1 = list.getUserName();
                    String number= list.getPassWord();

                    if(username.equals(username1)&&password.equals(number)) {

                        startActivity(new Intent(this,MainActivity.class));

                        return;
                    }
                    Toast.makeText(LoginActivity.this, "账户或密码错误", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
