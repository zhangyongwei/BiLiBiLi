package com.yongweizhang.bilibili.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by 张永卫on 2017/3/20.
 */

public class RecommendFragment extends BaseFragment {
    private TextView textView;
    @Override
    public View initView() {
        textView =new TextView(getActivity());
        textView.setText("RecommendFragment");
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        textView.setTextSize(20);
        return textView;
    }
}
