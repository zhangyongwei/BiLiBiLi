package com.yongweizhang.bilibili.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.yongweizhang.bilibili.R;
import com.yongweizhang.bilibili.adapter.CacheAdapter;
import com.yongweizhang.bilibili.childfragment.CacheFragment;
import com.yongweizhang.bilibili.childfragment.CenterFragment;
import com.yongweizhang.bilibili.fragment.BaseFragment;

import java.util.ArrayList;

import butterknife.InjectView;

public class DownloadActivity extends BaseActivity {


    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.tv_text)
    TextView tvText;
    @InjectView(R.id.iv_edit)
    ImageView ivEdit;
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
    private CacheAdapter adapter;

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

        initFragment();

        //设置适配器
        adapter = new CacheAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);

        //关联viewpager
        tablayout.setupWithViewPager(viewPager);
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private void initFragment() {

        fragments = new ArrayList<>();
        fragments.add(new CacheFragment());
        fragments.add(new CenterFragment());
    }

    @Override
    public void initTitle() {

    }

    @Override
    public int getLayoutid() {
        return R.layout.activity_download;
    }



}
