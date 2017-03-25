package com.yongweizhang.bilibili.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yongweizhang.bilibili.R;
import com.yongweizhang.bilibili.fragment.BaseFragment;
import com.yongweizhang.bilibili.fragment.HomeFragment;
import com.yongweizhang.bilibili.fragment.ItAfterlookFragment;
import com.yongweizhang.bilibili.fragment.ItHistoryFragment;
import com.yongweizhang.bilibili.fragment.ItLikeFragment;
import com.yongweizhang.bilibili.fragment.ItSelectorFragment;
import com.yongweizhang.bilibili.fragment.ItShouCangFragment;
import com.yongweizhang.bilibili.fragment.ItWalletFragment;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @InjectView(R.id.main_fl)
    FrameLayout mainFl;

    /**
     * 五个fragment的集合
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

    private ImageView iv_title;
    private ImageView iv_xin;
    private ImageView iv_night;
    private TextView tv_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        initFragment();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        iv_title = (ImageView) headerView.findViewById(R.id.iv_tltle);
        iv_xin = (ImageView) headerView.findViewById(R.id.iv_xin);
        iv_night = (ImageView) headerView.findViewById(R.id.iv_night);
        tv_name = (TextView) headerView.findViewById(R.id.tv_name);


        iv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "头像", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
        iv_xin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "信箱", Toast.LENGTH_SHORT).show();

            }
        });

        iv_night.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "夜晚", Toast.LENGTH_SHORT).show();

            }
        });
        tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "用户名", Toast.LENGTH_SHORT).show();

            }
        });


        navigationView.setNavigationItemSelectedListener(this);
        switchFragment(fragments.get(0));
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new ItAfterlookFragment());
        fragments.add(new ItShouCangFragment());
        fragments.add(new ItHistoryFragment());
        fragments.add(new ItLikeFragment());
        fragments.add(new ItWalletFragment());
        fragments.add(new ItSelectorFragment());
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.it_home) {

            position = 0;
        } else if (id == R.id.it_member) {

        } else if (id == R.id.it_memberpoints) {

        } else if (id == R.id.it_cache) {

        } else if (id == R.id.it_laterlook) {
            position = 1;
        } else if (id == R.id.it_collect) {
            position = 2;
        }else if (id == R.id.it_history) {
            position = 3;
        }else if (id == R.id.it_like) {
            position = 4;
        }else if (id == R.id.it_wallet) {
            position = 5;
        }else if (id == R.id.it_selector) {
            position = 6;
        }else if (id == R.id.it_help) {

        }

        //根据位置切换到对应的fragment
        Fragment currentFragment = fragments.get(position);
        switchFragment(currentFragment);




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

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
                ft.add(R.id.main_fl,currentFragment);
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

    /**
     * 布尔值判断是否双击退出
     */
    private boolean isDouble = false;
    /**
     * 点击两次退出
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode==KeyEvent.KEYCODE_BACK) {

            if(isDouble) {

                //退出
                finish();
            }
            Toast.makeText(MainActivity.this, "请再点击一次，退出应用", Toast.LENGTH_SHORT).show();
            isDouble = true;

            //超过两次就退出
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {

                    isDouble = false;
                }
            },2000);

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
