package com.zhe.basehttpasasync.manager;

import android.content.Context;

import com.zhe.basehttpasasync.http.BlkeeHttpInteface;
import com.zhe.basehttpasasync.http.BlkeeHttpManagerListener;
import com.zhe.basehttpasasync.ldhttp.LDHttpClient;

/**
 * Created by zhangyr on 2016/7/4.
 */
public class LDHttpManager {
    private static LDHttpManager ldHttpManager = new LDHttpManager();
    private final LDHttpClient httpClient;

    private LDHttpManager(){
        httpClient = new LDHttpClient();
    }
    public static LDHttpManager getInstance(){
        return ldHttpManager;
    }

    public void adv(Context context, BlkeeHttpInteface inteface, BlkeeHttpManagerListener listener){
        httpClient.handleRequest(context, inteface,listener);
    }

    public void httpCancle(Context context,boolean isCancle){
        httpClient.httpCancle(context,isCancle);
//        httpClient.cancleRequestHandle(isCancle);
    }

    public void loadFile(Context context, BlkeeHttpInteface inteface, BlkeeHttpManagerListener listener){
        httpClient.handleFileRequest(context, inteface,listener);
    }
}
