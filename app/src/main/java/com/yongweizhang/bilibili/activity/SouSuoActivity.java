package com.yongweizhang.bilibili.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yongweizhang.bilibili.R;
import com.yongweizhang.bilibili.adapter.SouSuoAdapter;
import com.yongweizhang.bilibili.childfragment.FirstFragment;
import com.yongweizhang.bilibili.childfragment.FourFragment;
import com.yongweizhang.bilibili.childfragment.SecondFragment;
import com.yongweizhang.bilibili.childfragment.ThreeFragment;
import com.yongweizhang.bilibili.fragment.BaseFragment;

import java.util.ArrayList;

import butterknife.InjectView;

public class SouSuoActivity extends BaseActivity {


    @InjectView(R.id.iv_exit)
    ImageView ivExit;
    @InjectView(R.id.tv_search)
    TextView tvSearch;
    @InjectView(R.id.iv_scan)
    ImageView ivScan;
    @InjectView(R.id.tablayout)
    TabLayout tablayout;
    @InjectView(R.id.view_pager)
    ViewPager viewPager;
    @InjectView(R.id.activity_sou_suo)
    LinearLayout activitySouSuo;
    private ArrayList<BaseFragment> fragments;
    @Override
    public void initListener() {

        String key = getIntent().getStringExtra("key");
        tvSearch.setText(key);

        ivExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initData() {

        initFragment();

        //设置适配器
        SouSuoAdapter adapter = new SouSuoAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);

        //关联viewpager
        tablayout.setupWithViewPager(viewPager);
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private void initFragment() {

        fragments = new ArrayList<>();
        fragments.add(new FirstFragment());
        fragments.add(new SecondFragment());
        fragments.add(new ThreeFragment());
        fragments.add(new FourFragment());
    }

    @Override
    public void initTitle() {

    }

    @Override
    public int getLayoutid() {
        return R.layout.activity_sou_suo;
    }


}
