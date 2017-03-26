package com.yongweizhang.bilibili.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wyt.searchbox.SearchFragment;
import com.wyt.searchbox.custom.IOnSearchClickListener;
import com.yongweizhang.bilibili.R;
import com.yongweizhang.bilibili.adapter.CacheAdapter;
import com.yongweizhang.bilibili.childfragment.CacheFragment;
import com.yongweizhang.bilibili.childfragment.CenterFragment;
import com.yongweizhang.bilibili.fragment.BaseFragment;

import java.util.ArrayList;

import butterknife.InjectView;
import butterknife.OnClick;

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




    @OnClick({R.id.iv_back, R.id.iv_edit, R.id.iv_download, R.id.iv_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_edit:
                Toast.makeText(DownloadActivity.this, "编辑", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_download:
                Toast.makeText(DownloadActivity.this, "设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_search:
//                Toast.makeText(DownloadActivity.this, "搜索", Toast.LENGTH_SHORT).show();
                SearchFragment searchFragment = SearchFragment.newInstance();
                searchFragment.setOnSearchClickListener(new IOnSearchClickListener() {

                    @Override

                    public void OnSearchClick(String keyword) {

//这里处理逻辑

                        Toast.makeText(DownloadActivity.this, keyword, Toast.LENGTH_SHORT).show();

                    }

                });

                searchFragment.show(getSupportFragmentManager(),SearchFragment.TAG);


                break;
        }
    }
}
