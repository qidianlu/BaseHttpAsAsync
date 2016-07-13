package com.zhe.basehttpasasync.http;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.Map;

import cz.msebera.android.httpclient.Header;

/**
 * Created by zhangyr on 2016/7/4.
 */
public class BlkeeHttpClient {
    private AsyncHttpClient httpClient;
    protected RequestParams params;
    protected String url;
    protected RequestHandle requestHandle;
    private Header[] headers;

    protected BlkeeHttpClient(){
        httpClient = new AsyncHttpClient(true,80,443);
    }

    public void setUrl(String url){
        this.url = url;
    }

    public String getUrl(){
        return url;
    }

    private void setParams(Map paramss) {
        this.params = new RequestParams(paramss);
        this.params.setUseJsonStreamer(true);
    }

    private void setHeaders(Header[] headers){
        this.headers = headers;
    }

    private RequestParams getParams() {
        return params;
    }

    private void handleRequest(Context context,HttpMethod httpMethod){
        BlkeeResponseHandlerInterface responseHandler = new BlkeeResponseHandlerInterface(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                handleSuccess(statusCode,headers,response);
                Log.e("jsonObjectClient:",response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                handleFailure(statusCode,headers,throwable,errorResponse);
            }
        };
        switch (httpMethod){
            case REQUEST_HTTP_GET:
                requestHandle = httpClient.get(context,url,headers,params,responseHandler);
                break;
            case REQUEST_HTTP_POST:
                requestHandle = httpClient.post(context,url,headers,params,null,responseHandler);
                break;
            case REQUEST_HTTP_PUT:
                requestHandle = httpClient.put(context,url,params,responseHandler);
                break;
            case REQUEST_HTTP_DELETE:
                requestHandle = httpClient.delete(context,url,headers,responseHandler);
                break;
            case REQUEST_HTTP_HEAD:
                requestHandle = httpClient.head(context,url,headers,params,responseHandler);
                break;
        }
    }

    private void handleBinaryRequest(Context context){
        httpClient.get(context, url, new BinaryHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] binaryData) {
                handleFileSuccess(statusCode,headers,binaryData);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] binaryData, Throwable error) {
                handleFileFailure(statusCode,headers,error,binaryData);
            }
        });

    }

    protected void handleSuccess(int statusCode,Header[] headers,Object responseObject){

    }

    protected void handleFailure(int statusCode,Header[] headers,Throwable error,Object responseObject){

    }

    protected void handleFileSuccess(int statusCode,Header[] headers,Object responseObject){

    }

    protected void handleFileFailure(int statusCode,Header[] headers,Throwable error,Object responseObject){

    }

    protected void blkeeHandleRequest(Context context,String url,Header[] headers,Map<String,Object> params,HttpMethod httpMethod){
        setUrl(url);
        setHeaders(headers);
        setParams(params);
        handleRequest(context,httpMethod);
    }

    protected void blkeeHandleFileRequest(Context context,String url){
        setUrl(url);
        handleBinaryRequest(context);
    }

    public void httpCancle(Context context,boolean isCancle){
        httpClient.cancelRequests(context,isCancle);
    }

    public void cancleAllRequest(boolean mayInterruptIfRunning){
        httpClient.cancelAllRequests(mayInterruptIfRunning);
    }

    public void cancleRequestHandle(boolean isCancled){
        if(requestHandle!=null){
            requestHandle.cancel(isCancled);
        }else{
        }
    }
}
