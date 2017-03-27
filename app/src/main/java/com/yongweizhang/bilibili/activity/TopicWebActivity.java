package com.yongweizhang.bilibili.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.yongweizhang.bilibili.R;
import com.yongweizhang.bilibili.adapter.TopicAdapter;
import com.yongweizhang.bilibili.utils.ClipboardUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class TopicWebActivity extends AppCompatActivity {


    @InjectView(R.id.share_toolbar)
    Toolbar shareToolbar;
    @InjectView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @InjectView(R.id.wb_info_more)
    WebView wbInfoMore;
    @InjectView(R.id.activity_live_info)
    LinearLayout activityLiveInfo;
    private Intent intent;
    private String titles;
    private String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_web);
        ButterKnife.inject(this);

        initData();
        loadWeb();
    }

    private void initData() {

        String titles = getIntent().getStringExtra(TopicAdapter.KEY);
        String link = getIntent().getStringExtra(TopicAdapter.VALUE);

        if (!TextUtils.isEmpty(link)) {
            wbInfoMore.loadUrl(link);
        }
        shareToolbar.setTitle(TextUtils.isEmpty(titles) ? "详情" : titles);
//        shareToolbar.setTitle( "详情" );

        setSupportActionBar(shareToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    private void initLinstener() {


    }


    private void loadWeb() {


        WebSettings webSettings = wbInfoMore.getSettings();
        //设置支持js
        webSettings.setJavaScriptEnabled(true);
        //设置添加缩放按钮
        webSettings.setBuiltInZoomControls(true);
        //设置双击单击
        webSettings.setUseWideViewPort(true);
        //设置WebViewClient，如果没有设置，调起系统的浏览器打开新的连接
        wbInfoMore.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);

                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                //判断新版本
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    view.loadUrl(request.getUrl().toString());
                }

                return true;
            }
        });



        if (!TextUtils.isEmpty(link)) {
            wbInfoMore.loadUrl(link);

        }
//        if (!TextUtils.isEmpty(title)) {
//
//            tvText.setText(title);
//        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.topic_share, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.menu_share:
                share();
                break;
            case R.id.menu_browseropen:
                intent = new Intent(Intent.ACTION_VIEW);
                startActivity(intent);
                break;
            case R.id.menu_copylink:
                ClipboardUtil.setText(TopicWebActivity.this, link);
                Toast.makeText(this, "已复制", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    //分享法
    private void share() {
        intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
        intent.putExtra(Intent.EXTRA_TEXT, "来自「哔哩哔哩」的分享:" + link);
        startActivity(Intent.createChooser(intent, titles));
    }

    @Override
    public void onBackPressed() {
        if (wbInfoMore.canGoBack() && wbInfoMore.copyBackForwardList().getSize() > 0
                && !wbInfoMore.getUrl().equals(wbInfoMore.copyBackForwardList().getItemAtIndex(0)
                .getOriginalUrl())) {
            wbInfoMore.goBack();
        } else {
            finish();
        }
    }
    @Override
    protected void onDestroy() {
        wbInfoMore.destroy();
        super.onDestroy();
    }
}
