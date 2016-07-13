package com.zhe.basehttpasasync.http;

import java.util.Map;

import cz.msebera.android.httpclient.Header;

/**
 * Created by zhangyr on 2016/7/4.
 */
public interface BlkeeHttpInteface {
    String getBaseUrl();
    String getRequestUrl();
    String getApiUrl();
    int setTimeOut();
    HttpMethod getRequestMethod();
    Header[] getRequestHeader();
    Map<String,Object> getRequestParams();
    void setResponseObject(Object object);
    void setResponseError(Throwable error);
    void setResponseHeader(Header[] headers);
    void setResultCode(int code);
    void setResultMsg(String msg);
}
