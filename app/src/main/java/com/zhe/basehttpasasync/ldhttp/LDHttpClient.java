package com.zhe.basehttpasasync.ldhttp;

import android.content.Context;

import com.zhe.basehttpasasync.http.BlkeeHttpClient;
import com.zhe.basehttpasasync.http.BlkeeHttpInteface;
import com.zhe.basehttpasasync.http.BlkeeHttpManagerListener;
import com.zhe.basehttpasasync.http.HttpMethod;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import cz.msebera.android.httpclient.Header;

/**
 * Created by zhangyr on 2016/7/4.
 */
public class LDHttpClient extends BlkeeHttpClient {
    private static final String resultKey = "result";
    private static final String resultCodeKey = "result_code";
    private static final String resultMsgKey = "result_msg";
    private String BASE_URL = "http://testapp.blkee.com/api/";
    private String url;
    public int responseResultCode;
    public String responseResultMsg;
    public Object object;
    private BlkeeHttpManagerListener listener;
    private BlkeeHttpInteface blkeeHttpInteface;

    @Override
    protected void handleSuccess(int statusCode, Header[] headers, Object responseObject) {
        super.handleSuccess(statusCode, headers, responseObject);
        try {
            JSONObject jsonObject = (JSONObject) responseObject;
            responseResultCode = jsonObject.getInt(resultCodeKey);
            responseResultMsg = jsonObject.getString(resultMsgKey);
            object = jsonObject.get(resultKey);
            blkeeHttpInteface.setResponseHeader(headers);
            blkeeHttpInteface.setResponseObject(object);
            blkeeHttpInteface.setResultCode(responseResultCode);
            blkeeHttpInteface.setResultMsg(responseResultMsg);
            listener.run(blkeeHttpInteface);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void handleFailure(int statusCode, Header[] headers, Throwable error, Object responseObject) {
        super.handleFailure(statusCode, headers, error, responseObject);
        blkeeHttpInteface.setResponseHeader(headers);
        blkeeHttpInteface.setResponseError(error);
        blkeeHttpInteface.setResponseObject(responseObject);
        listener.run(blkeeHttpInteface);
    }

    @Override
    protected void handleFileSuccess(int statusCode, Header[] headers, Object responseObject) {
        super.handleFileSuccess(statusCode, headers, responseObject);
        byte[] binaryData = (byte[]) responseObject;
        blkeeHttpInteface.setResponseObject(binaryData);
        listener.run(blkeeHttpInteface);
    }

    @Override
    protected void handleFileFailure(int statusCode, Header[] headers, Throwable error, Object responseObject) {
        super.handleFileFailure(statusCode, headers, error, responseObject);
        blkeeHttpInteface.setResponseObject(responseObject);
        blkeeHttpInteface.setResponseError(error);
        listener.run(blkeeHttpInteface);
    }

    @Override
    protected void blkeeHandleRequest(Context context, String url, Header[] headers, Map<String, Object> params, HttpMethod httpMethod) {

        super.blkeeHandleRequest(context, url, headers, params, httpMethod);
    }

    @Override
    protected void blkeeHandleFileRequest(Context context, String url) {
        super.blkeeHandleFileRequest(context, url);
    }

    public void handleRequest(Context context, BlkeeHttpInteface blkeeHttpInteface, BlkeeHttpManagerListener listener) {
        this.listener = listener;
        this.blkeeHttpInteface = blkeeHttpInteface;
        blkeeHandleRequest(context,
                blkeeHttpInteface.getRequestUrl(),
                blkeeHttpInteface.getRequestHeader(),
                blkeeHttpInteface.getRequestParams(),
                blkeeHttpInteface.getRequestMethod());
    }

    public void handleFileRequest(Context context,BlkeeHttpInteface blkeeHttpInteface,BlkeeHttpManagerListener listener){
        this.listener = listener;
        this.blkeeHttpInteface = blkeeHttpInteface;
        blkeeHandleFileRequest(context,blkeeHttpInteface.getRequestUrl());
    }

}
