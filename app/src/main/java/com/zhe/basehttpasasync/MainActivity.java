package com.zhe.basehttpasasync;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zhe.basehttpasasync.http.BlkeeHttpInteface;
import com.zhe.basehttpasasync.http.BlkeeHttpManagerListener;
import com.zhe.basehttpasasync.ldhttp.AdvApi;
import com.zhe.basehttpasasync.ldhttp.LoadFileApi;
import com.zhe.basehttpasasync.manager.LDHttpManager;
import com.zhe.basehttpasasync.util.StartPic;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private AdvApi advApi;
    private Button mButton_http,button_destory;
    private LoadFileApi loadFileApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton_http = (Button) findViewById(R.id.button_http);
        button_destory = (Button)findViewById(R.id.button_destory);
        initListener();
    }

    private void initListener(){
        mButton_http.setOnClickListener(this);
        button_destory.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_http:
                initData();
                break;
            case R.id.button_destory:
                Intent intent = new Intent(this,SecondActivity.class);
                startActivity(intent);
                this.finish();
                break;
        }
    }

    private void initData(){
        advApi = new AdvApi();
        LDHttpManager.getInstance().adv(MainActivity.this, advApi, new BlkeeHttpManagerListener() {
            @Override
            public void run(BlkeeHttpInteface httpInteface) {
                advApi = (AdvApi)httpInteface;
                if(advApi.getError()==null){
                    ArrayList<StartPic> list =  advApi.getList();
                    for(int i=0;i<list.size();i++){
                        loadPic(list.get(i).pic);
                    }
                }
            }
        });

    }

    private void loadPic(String url){
        loadFileApi = new LoadFileApi(this,url);
        LDHttpManager.getInstance().loadFile(this, loadFileApi, new BlkeeHttpManagerListener() {
            @Override
            public void run(BlkeeHttpInteface httpInteface) {
                loadFileApi = (LoadFileApi)httpInteface;
                if(loadFileApi.getError()==null){
                    loadFileApi.getBitmap();
                    Log.e("bitmap:","--------------------");
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LDHttpManager.getInstance().httpCancle(MainActivity.this,true);
    }
}
