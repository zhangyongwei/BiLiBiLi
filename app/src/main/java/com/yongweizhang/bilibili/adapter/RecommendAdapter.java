package com.yongweizhang.bilibili.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yongweizhang.bilibili.fragment.BaseFragment;

import java.util.ArrayList;

/**
 * Created by 张永卫on 2017/3/23.
 */

public class RecommendAdapter extends FragmentPagerAdapter {
    private final ArrayList<BaseFragment> fragments;
    private String[] titles = new String[]{"综合","动态"};
    public RecommendAdapter(FragmentManager fragmentManager, ArrayList<BaseFragment> fragments) {
        super(fragmentManager);

        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
