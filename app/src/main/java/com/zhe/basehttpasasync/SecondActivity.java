package com.zhe.basehttpasasync;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by zhangyr on 2016/7/6.
 */
public class SecondActivity extends AppCompatActivity{

    private ListView mListview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_second);
        mListview = (ListView) findViewById(R.id.listview);
        ArrayList<String> list = new ArrayList<>();
        for(int i=0;i<100;i++){
            list.add("第"+i+"个");
        }
        mListview.setAdapter(new ListAdapter(list));
    }

    class ListAdapter extends BaseAdapter{
        private ArrayList<String> list;
        ListAdapter(ArrayList<String> list){
            this.list = list;
        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView view = new TextView(SecondActivity.this);
            view.setText(list.get(position));
            return view;
        }
    }
}
