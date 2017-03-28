package com.yongweizhang.bilibili.childfragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.yongweizhang.bilibili.fragment.BaseFragment;

/**
 * Created by 张永卫on 2017/3/27.
 */

public class ThreeFragment extends BaseFragment {
    private TextView textView;
    @Override
    public View initView() {
        textView = new TextView(mContext);
        textView.setText("ThreeFragment");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(20);
        textView.setTextColor(Color.RED);
        return textView;
    }
}
