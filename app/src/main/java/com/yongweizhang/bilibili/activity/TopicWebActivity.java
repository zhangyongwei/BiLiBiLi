package com.yongweizhang.bilibili.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yongweizhang.bilibili.R;
import com.yongweizhang.bilibili.adapter.TopicAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class TopicWebActivity extends AppCompatActivity {

    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.tv_text)
    TextView tvText;
    @InjectView(R.id.iv_more)
    ImageView ivMore;
    @InjectView(R.id.wb_info_more)
    WebView wbInfoMore;
    @InjectView(R.id.activity_live_info)
    LinearLayout activityLiveInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_web);
        ButterKnife.inject(this);

        loadWeb();
        initLinstener();
    }

    private void initLinstener() {

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void loadWeb() {


        String title = getIntent().getStringExtra(TopicAdapter.KEY);
        String link = getIntent().getStringExtra(TopicAdapter.VALUE);


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

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    view.loadUrl(request.getUrl().toString());
                }

                return true;
            }
        });

        if(!TextUtils.isEmpty(link)) {
            wbInfoMore.loadUrl(link);

        }
        if(!TextUtils.isEmpty(title)) {

            tvText.setText(title);
        }

    }

}
