package com.yongweizhang.bilibili.activity;

import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
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
import com.yongweizhang.bilibili.view.MyApplication;

import java.util.List;

import butterknife.InjectView;

import static android.R.attr.id;

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
    private UserDao userDao;


    @Override
    public void initListener() {

        btnPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = etWhere.getText().toString().trim();
                String passWord = etCall.getText().toString().trim();
                //判断两个密码是否一致  判断密码的长度  判断是否注册过
                List<User> users = userDao.loadAll();
                if(users!=null && users.size()>0){


                }
                if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(passWord)) {



                    userDao.insert(new User(Long.valueOf(id),userName,passWord));
                    Toast.makeText(RegistActivity.this, "注册成功", Toast.LENGTH_SHORT).show();

                }else{

                    if (TextUtils.isEmpty(userName) && TextUtils.isEmpty(passWord)) {
                        Toast.makeText(RegistActivity.this, "id为空", Toast.LENGTH_SHORT).show();
                    }


                }

                etWhere.setText("");
                etCall.setText("");
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

        tlLocal.setHint("请输入你的账号:");
        tlPhone.setHint("请输入你的密码:");

        userDao = MyApplication.getInstances().getDaoSession().getUserDao();



    }

    @Override
    public void initTitle() {

    }

    @Override
    public int getLayoutid() {
        return R.layout.activity_regist;
    }


}
