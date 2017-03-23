package com.yongweizhang.bilibili.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.yongweizhang.bilibili.R;
import com.yongweizhang.bilibili.bean.HomeBean;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LiveInfoActivity extends AppCompatActivity {

    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.tv_text)
    TextView tvText;
    @InjectView(R.id.iv_more)
    ImageView ivMore;
    @InjectView(R.id.wb_info_more)
    WebView wbInfoMore;
    @InjectView(R.id.progressbar)
    ProgressBar progressbar;
    @InjectView(R.id.activity_live_info)
    LinearLayout activityLiveInfo;

    private HomeBean.DataBean.BannerBean bannerBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_info);
        ButterKnife.inject(this);

        getData();
    }

    private void getData() {

        bannerBean = (HomeBean.DataBean.BannerBean) getIntent().getSerializableExtra("banner");

        setData();
    }

    private void setData() {

        tvText.setText(bannerBean.getRemark());

        loadWeb("http://live.bilibili.com/hd/valentinesDay2ndH5");
    }

    private void loadWeb(String url) {

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
                progressbar.setVisibility(View.GONE);
            }


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);

                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    view.loadUrl(request.getUrl().toString());
                }

                return true;
            }
        });

        wbInfoMore.loadUrl(url);
    }

    @OnClick({R.id.iv_back, R.id.iv_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_more:

                Toast.makeText(LiveInfoActivity.this, "更多", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wbInfoMore.canGoBack()) {
            wbInfoMore.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
