package com.yongweizhang.bilibili.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.yongweizhang.bilibili.R;
import com.yongweizhang.bilibili.adapter.RankAdapter;
import com.yongweizhang.bilibili.childfragment.FanJuFragment;
import com.yongweizhang.bilibili.childfragment.QuanZhanFragment;
import com.yongweizhang.bilibili.childfragment.YuanChauangFragment;
import com.yongweizhang.bilibili.fragment.BaseFragment;

import java.util.ArrayList;

import butterknife.InjectView;

public class RankActivity extends BaseActivity {


    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.tv_text)
    TextView tvText;
    @InjectView(R.id.iv_download)
    ImageView ivDownload;
    @InjectView(R.id.iv_search)
    ImageView ivSearch;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tablayout)
    TabLayout tablayout;
    @InjectView(R.id.view_pager)
    ViewPager viewPager;

    private ArrayList<BaseFragment> fragments;
    private RankAdapter adapter;


    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

        initFragment();

        //设置适配器
        adapter = new RankAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);

        //关联viewpager
        tablayout.setupWithViewPager(viewPager);
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private void initFragment() {

        fragments = new ArrayList<>();

        fragments.add(new YuanChauangFragment());
        fragments.add(new QuanZhanFragment());
        fragments.add(new FanJuFragment());
    }

    @Override
    public void initTitle() {

    }

    @Override
    public int getLayoutid() {
        return R.layout.activity_rank;
    }

}
