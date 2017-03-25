package com.yongweizhang.bilibili.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yongweizhang.bilibili.fragment.BaseFragment;

import java.util.ArrayList;

/**
 * Created by 张永卫on 2017/3/26.
 */

public class CacheAdapter extends FragmentPagerAdapter {
    private final ArrayList<BaseFragment> fragments;
    private String[] titles = new String[]{"已缓存","缓存中"};

    public CacheAdapter(FragmentManager supportFragmentManager, ArrayList<BaseFragment> fragments) {
        super(supportFragmentManager);
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
