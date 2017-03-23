package com.yongweizhang.bilibili.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.yongweizhang.bilibili.R;
import com.yongweizhang.bilibili.adapter.RecommendAdapter;
import com.yongweizhang.bilibili.childfragment.DynamicFragment;
import com.yongweizhang.bilibili.childfragment.SynthesizeFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 张永卫on 2017/3/20.
 */

public class RecommendFragment extends BaseFragment {


    @InjectView(R.id.recommend_tablayout)
    TabLayout recommendTablayout;
    @InjectView(R.id.recommend_view_pager)
    ViewPager recommendViewPager;
    private RecommendAdapter adapter;

    private ArrayList<BaseFragment> fragments;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_recommend, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        initFragment();
        //设置适配器
        adapter = new RecommendAdapter(getChildFragmentManager(), fragments);
        recommendViewPager.setAdapter(adapter);

        //关联viewpager
        recommendTablayout.setupWithViewPager(recommendViewPager);
        recommendTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private void initFragment() {

        fragments = new ArrayList<>();
        fragments.add(new SynthesizeFragment());//综合
        fragments.add(new DynamicFragment());//动态
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


}
