package com.yongweizhang.bilibili.shopfragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.yongweizhang.bilibili.fragment.BaseFragment;

/**
 * Created by 张永卫on 2017/3/21.
 */

public class SanFragment extends BaseFragment {

    private TextView textView;
    @Override
    public View initView() {

        textView = new TextView(getActivity());
        textView.setText("SanFragment");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(20);
        textView.setTextColor(Color.RED);
        return textView;
    }
}
