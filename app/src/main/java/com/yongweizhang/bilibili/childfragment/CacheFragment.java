package com.yongweizhang.bilibili.childfragment;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yongweizhang.bilibili.R;
import com.yongweizhang.bilibili.fragment.BaseFragment;
import com.yongweizhang.bilibili.progress.MyProgressBar;
import com.yongweizhang.bilibili.progress.ResponseListener;
import com.yongweizhang.bilibili.progress.RetrofitUtils;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 张永卫on 2017/3/26.
 */

public class CacheFragment extends BaseFragment {


    @InjectView(R.id.tv_progress)
    TextView tvProgress;
    @InjectView(R.id.bt_download)
    Button btDownload;
    File file;
    @InjectView(R.id.progressbar)
    MyProgressBar progressbar;

    @Override
    public View initView() {

        View view = View.inflate(mContext, R.layout.fragment_cache, null);
        ButterKnife.inject(this, view);

        initListener();

        return view;


    }

    @Override
    public void initData() {
        super.initData();
        file = new File(getActivity().getExternalFilesDir(null), "1.apk");
    }

    private void initListener() {

        btDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btDownload.setText("下载中");
                btDownload.setEnabled(false);

                RetrofitUtils.getInstance().download(file, new ResponseListener() {
                    @Override
                    public void onProgress(final long progress, final long total, boolean done) {

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.e("TAG", "正在下载");
                                long l = progress * 100 / total;
                                progressbar.setProgress((int) l);

                                String pro = (int) progress * 100 / (int) total + "%";
                                String p = RetrofitUtils.formetFileSize(progress);
                                String t = RetrofitUtils.formetFileSize(total);
                                tvProgress.setText(p + " / " + t + "\t" + pro);
                            }
                        });
                    }

                    @Override
                    public void onResponse() {

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                btDownload.setText("下载完成!");
                            }
                        });
                    }

                    @Override
                    public void onFailure(final String error) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                btDownload.setText("下载出错!");
                                btDownload.setEnabled(true);
                                Log.e("TAG", "CacheFragment onFailure()" + error);
                            }
                        });
                    }
                });
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }



}
