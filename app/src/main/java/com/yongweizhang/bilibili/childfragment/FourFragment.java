package com.yongweizhang.bilibili.childfragment;

import android.view.View;
import android.widget.ImageView;

import com.yongweizhang.bilibili.R;
import com.yongweizhang.bilibili.fragment.BaseFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 张永卫on 2017/3/27.
 */

public class FourFragment extends BaseFragment {

    @InjectView(R.id.iv_imag)
    ImageView ivImag;
    @InjectView(R.id.iv_text)
    ImageView ivText;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fanju_item, null);
        ButterKnife.inject(this, view);
        return view;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
