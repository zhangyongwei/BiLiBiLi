package com.yongweizhang.bilibili.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yongweizhang.bilibili.fragment.BaseFragment;

import java.util.ArrayList;

/**
 * Created by 张永卫on 2017/3/21.
 */

public class HomeViewPagerAdapter extends FragmentPagerAdapter {


    private final ArrayList<BaseFragment> fragments;

    private String[] titles = new String[]{"直播","推荐","追番","分区","发现"};

    public HomeViewPagerAdapter(FragmentManager fragmentManager, ArrayList<BaseFragment> fragments) {
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
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
