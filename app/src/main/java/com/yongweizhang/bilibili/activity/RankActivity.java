package com.yongweizhang.bilibili.activity;

import android.content.Intent;
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
    public void initListener() {

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ivDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(RankActivity.this, "下载更多", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RankActivity.this,DownloadActivity.class);
                startActivity(intent);

            }
        });

        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(RankActivity.this, "搜索", Toast.LENGTH_SHORT).show();

                SearchFragment searchFragment = SearchFragment.newInstance();
                searchFragment.setOnSearchClickListener(new IOnSearchClickListener() {

                    @Override

                    public void OnSearchClick(String keyword) {

//这里处理逻辑

                        Toast.makeText(RankActivity.this, keyword, Toast.LENGTH_SHORT).show();

                    }

                });

                searchFragment.show(getSupportFragmentManager(),SearchFragment.TAG);

            }
        });
    }

    @Override
    public void initTitle() {

    }

    @Override
    public int getLayoutid() {
        return R.layout.activity_rank;
    }

}
