package com.yongweizhang.bilibili.fragment;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.yongweizhang.bilibili.R;
import com.yongweizhang.bilibili.adapter.HomeViewPagerAdapter;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by 张永卫on 2017/3/21.
 */

public class HomeFragment extends BaseFragment {


    @InjectView(R.id.iv_home)
    ImageView ivHome;
    @InjectView(R.id.iv_noface)
    ImageView ivNoface;
    @InjectView(R.id.iv_game)
    ImageView ivGame;
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
    @InjectView(R.id.fab)
    FloatingActionButton fab;
//    @InjectView(R.id.mySwipeRefreshLayout)
//    SwipeRefreshLayout mySwipeRefreshLayout;
    private HomeViewPagerAdapter adapter;
    private ArrayList<BaseFragment> fragments;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        initFragment();

        //设置适配器
        adapter = new HomeViewPagerAdapter(getFragmentManager(), fragments);
        viewPager.setAdapter(adapter);

        //关联viewpager
        tablayout.setupWithViewPager(viewPager);
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);

//        //设置下拉出现小圆圈是否是缩放出现，出现的位置，最大的下拉位置
//        mySwipeRefreshLayout.setProgressViewOffset(true, 50, 200);
//
//        //设置下拉圆圈的大小，两个值 LARGE， DEFAULT
//        mySwipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
//
//        // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
//        mySwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_red_light);
//
//        iniListener();

    }

//    private void iniListener() {
//
//        mySwipeRefreshLayout.setOnRefreshListener(
//                new SwipeRefreshLayout.OnRefreshListener() {
//                    @Override
//                    public void onRefresh() {
//                        // 刷新动画开始后回调到此方法
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//
//
//                            }
//                        }, 2000);
//                    }
//                }
//        );
//    }


    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new LiveFragment());
        fragments.add(new RecommendFragment());
        fragments.add(new AfterSomeFragment());
        fragments.add(new PartitionFragment());
        fragments.add(new CommunityFragment());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    @OnClick({R.id.iv_home, R.id.iv_noface, R.id.iv_game, R.id.iv_download, R.id.iv_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_home:
                Toast.makeText(getActivity(), "更多", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_noface:
                Toast.makeText(getActivity(), "头像", Toast.LENGTH_SHORT).show();

                break;
            case R.id.iv_game:
                Toast.makeText(getActivity(), "游戏", Toast.LENGTH_SHORT).show();

                break;
            case R.id.iv_download:
                Toast.makeText(getActivity(), "下载", Toast.LENGTH_SHORT).show();

                break;
            case R.id.iv_search:
                Toast.makeText(getActivity(), "搜索", Toast.LENGTH_SHORT).show();

                break;
        }
    }



}
