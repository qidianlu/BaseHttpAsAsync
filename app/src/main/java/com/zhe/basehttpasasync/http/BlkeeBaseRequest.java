package com.zhe.basehttpasasync.http;

import java.util.Map;

import cz.msebera.android.httpclient.Header;

/**
 * Created by zhangyr on 2016/7/4.
 */
public class BlkeeBaseRequest implements BlkeeHttpInteface{
    protected Throwable error;
    protected int responseResultCode;
    protected String responseResultMsg;
    @Override
    public String getBaseUrl() {
        return "";
    }

    @Override
    public String getRequestUrl() {
        return getBaseUrl()+getApiUrl();
    }

    @Override
    public String getApiUrl() {
        return "";
    }

    @Override
    public int setTimeOut() {
        return 0;
    }

    @Override
    public HttpMethod getRequestMethod() {
        return null;
    }

    @Override
    public Header[] getRequestHeader() {
        return new Header[0];
    }

    @Override
    public Map<String, Object> getRequestParams() {
        return null;
    }

    @Override
    public void setResponseObject(Object object) {

    }

    @Override
    public void setResponseError(Throwable error) {
        this.error = error;
    }

    @Override
    public void setResponseHeader(Header[] headers) {

    }

    @Override
    public void setResultCode(int code) {
        this.responseResultCode = code;
    }

    @Override
    public void setResultMsg(String msg) {
        this.responseResultMsg = msg;
    }

    public Throwable getError() {
        return error;
    }
}
