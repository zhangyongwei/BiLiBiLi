package com.yongweizhang.bilibili.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yongweizhang.bilibili.R;
import com.yongweizhang.bilibili.fragment.BaseFragment;
import com.yongweizhang.bilibili.shopfragment.OneFragment;
import com.yongweizhang.bilibili.shopfragment.SanFragment;
import com.yongweizhang.bilibili.shopfragment.TwoFragment;

import java.util.ArrayList;

import butterknife.InjectView;

public class ShoppingActivity extends BaseActivity {


    @InjectView(R.id.fl_main)
    FrameLayout flMain;
    @InjectView(R.id.rb_home)
    RadioButton rbHome;
    @InjectView(R.id.rb_cart)
    RadioButton rbCart;
    @InjectView(R.id.rb_user)
    RadioButton rbUser;
    @InjectView(R.id.rg_main)
    RadioGroup rgMain;
    @InjectView(R.id.activity_shopping)
    LinearLayout activityShopping;

    /**
     * 三个fragment的集合
     */
    private ArrayList<BaseFragment> fragments;
    /**
     * Fragment对应的位置
     */
    private int position;
    /**
     * 刚才被选中的fragment
     */
    private Fragment tempFragment;
    private FragmentTransaction ft;

    @Override
    public void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){

                    case R.id.rb_home:
                        position = 0;
                        break;
                    case R.id.rb_cart:
                        position = 1;
                        break;
                    case R.id.rb_user:
                        position = 2;
                        break;
                }
                //根据位置切换到对应的fragment
                Fragment currentFragment = fragments.get(position);
                switchFragment(currentFragment);
            }
        });
        //默认选中首页---注意默认选择放在后面
        rgMain.check(R.id.rb_home);
    }
    /**
     * 切换fragment
     * @param currentFragment
     */
    private void switchFragment(Fragment currentFragment) {
        //切换的不是同一个页面
        if(tempFragment!=currentFragment) {
            //得到FragmentManager
            ft = getSupportFragmentManager().beginTransaction();
            //如果没有添加就添加
            if(!currentFragment.isAdded()) {

                //缓存的隐藏
                if(tempFragment!=null) {

                    ft.hide(tempFragment);
                }

                //添加
                ft.add(R.id.fl_main,currentFragment);
            }else{
                //缓存的隐藏
                if(tempFragment!=null) {
                    ft.hide(tempFragment);
                }

                //显示
                ft.show(currentFragment);


            }
            //提交事务
            ft.commit();

            //把当前的赋值成缓存的
            tempFragment = currentFragment;
        }


    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int id = intent.getIntExtra("checkedid", R.id.rb_home);
        rgMain.check(id);

    }
    @Override
    public void initData() {

        initFragment();

    }

    private void initFragment() {

        fragments = new ArrayList<>();
        fragments.add(new OneFragment());//主页面
        fragments.add(new TwoFragment());//购物车
        fragments.add(new SanFragment());//用户
    }

    @Override
    public void initTitle() {

    }

    @Override
    public int getLayoutid() {
        return R.layout.activity_shopping;
    }


}
