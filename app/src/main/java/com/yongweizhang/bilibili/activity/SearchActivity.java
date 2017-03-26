package com.yongweizhang.bilibili.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yongweizhang.bilibili.R;

import butterknife.InjectView;

public class SearchActivity extends BaseActivity {


    @InjectView(R.id.iv_exit)
    ImageView ivExit;
    @InjectView(R.id.et_search)
    EditText etSearch;
    @InjectView(R.id.iv_default)
    ImageView ivDefault;
    @InjectView(R.id.activity_search)
    LinearLayout activitySearch;

    @Override
    public void initListener() {

        ivExit.setOnClickListener(new View.OnClickListener() {
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
        return R.layout.activity_search;
    }


}
