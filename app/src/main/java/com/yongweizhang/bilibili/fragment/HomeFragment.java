package com.yongweizhang.bilibili.fragment;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.wyt.searchbox.SearchFragment;
import com.wyt.searchbox.custom.IOnSearchClickListener;
import com.yongweizhang.bilibili.R;
import com.yongweizhang.bilibili.activity.DownloadActivity;
import com.yongweizhang.bilibili.activity.SouSuoActivity;
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
    private Intent intent;

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


    }


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
//                Toast.makeText(getActivity(), "登录", Toast.LENGTH_SHORT).show();
//                intent = new Intent(mContext, LoginActivity.class);
//                startActivity(intent);
                DrawerLayout drawer = (DrawerLayout)getActivity(). findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.START);
                break;
            case R.id.iv_noface:
                Toast.makeText(getActivity(), "头像", Toast.LENGTH_SHORT).show();

                break;
            case R.id.iv_game:
                Toast.makeText(getActivity(), "游戏", Toast.LENGTH_SHORT).show();

                break;
            case R.id.iv_download:
//                Toast.makeText(getActivity(), "下载", Toast.LENGTH_SHORT).show();
                intent = new Intent(mContext, DownloadActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_search:
//                Toast.makeText(getActivity(), "搜索", Toast.LENGTH_SHORT).show();

                SearchFragment searchFragment = SearchFragment.newInstance();
                searchFragment.setOnSearchClickListener(new IOnSearchClickListener() {

                    @Override

                    public void OnSearchClick(String keyword) {

//这里处理逻辑
                        Intent intent = new Intent(getActivity(), SouSuoActivity.class);

                        intent.putExtra("key",keyword);

                        mContext.startActivity(intent);
//                        Toast.makeText(mContext, keyword, Toast.LENGTH_SHORT).show();

                    }

                });

                searchFragment.show(getFragmentManager(),SearchFragment.TAG);
                break;
        }
    }



}
