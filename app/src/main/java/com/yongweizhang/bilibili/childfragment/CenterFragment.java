package com.yongweizhang.bilibili.childfragment;

import android.view.View;

import com.yongweizhang.bilibili.R;
import com.yongweizhang.bilibili.fragment.BaseFragment;

/**
 * Created by 张永卫on 2017/3/26.
 */

public class CenterFragment extends BaseFragment {
    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_cache,null);

        return view;
    }
}
