package com.yongweizhang.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ListView lv_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lv_main = (ListView) findViewById(R.id.lv_main);

        lv_main.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 10;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder viewHolder;
                if (convertView == null) {

                    convertView = View.inflate(MainActivity.this, R.layout.listview, null);

                    viewHolder = new ViewHolder();

                    convertView.setTag(viewHolder);



                    viewHolder.tv_main = (TextView) convertView.findViewById(R.id.tv_main);
                } else {

                    viewHolder = (ViewHolder) convertView.getTag();
                }

                viewHolder.tv_main.setText("lllll");
                return convertView;
            }
        });
    }

    class ViewHolder {

        private TextView tv_main;


    }

}
