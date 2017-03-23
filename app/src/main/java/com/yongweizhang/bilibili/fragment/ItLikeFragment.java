package com.yongweizhang.bilibili.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by 张永卫on 2017/3/21.
 */

public class ItLikeFragment extends BaseFragment {
    private TextView textView;
    @Override
    public View initView() {

        textView = new TextView(getActivity());
        textView.setText("ItLikeFragment");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(20);
        textView.setTextColor(Color.RED);
        return textView;
    }
}
