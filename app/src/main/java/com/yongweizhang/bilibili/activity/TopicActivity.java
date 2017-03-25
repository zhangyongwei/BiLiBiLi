package com.yongweizhang.bilibili.activity;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.yongweizhang.bilibili.R;
import com.yongweizhang.bilibili.adapter.TopicAdapter;
import com.yongweizhang.bilibili.bean.TopicBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.InjectView;
import okhttp3.Call;

public class TopicActivity extends BaseActivity {

    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.lv_list)
    ListView lvList;
    @InjectView(R.id.activity_topic)
    LinearLayout activityTopic;



    @Override
    public void initData() {

        getDataFromNet();
    }
    String url = "http://api.bilibili.com/topic/getlist?appkey=1d8b6e7d45233436&build=501000&mobi_app=android&page=1&pageSize=20&platform=android&ts=1490015740000&sign=be68382cdc99c168ef87f2fa423dd280";
    private void getDataFromNet() {

        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                        Log.e("TAG", "error="+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {

                        Log.e("TAG", "response="+response);

                        processData(response);
                    }
                });
    }

    private void processData(String json) {

        TopicBean topicBean = JSON.parseObject(json, TopicBean.class);

        Log.e("TAG", "topicBean=="+topicBean);

        //设置适配器
        TopicAdapter adapter = new TopicAdapter(this,topicBean);
        lvList.setAdapter(adapter);

    }

    @Override
    public void initListener() {

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });


    }

    @Override
    public void initTitle() {

    }

    @Override
    public int getLayoutid() {
        return R.layout.activity_topic;
    }
}
