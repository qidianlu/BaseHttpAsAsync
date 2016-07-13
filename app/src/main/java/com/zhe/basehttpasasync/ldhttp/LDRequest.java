package com.zhe.basehttpasasync.ldhttp;

import com.zhe.basehttpasasync.http.BlkeeBaseRequest;
import com.zhe.basehttpasasync.http.HttpMethod;

import java.util.Map;

import cz.msebera.android.httpclient.Header;

/**
 * Created by zhangyr on 2016/7/12.
 */
public class LDRequest extends BlkeeBaseRequest{

    @Override
    public String getBaseUrl() {
        return "https://app.blkee.com/api/";
    }

    @Override
    public HttpMethod getRequestMethod() {
        return HttpMethod.REQUEST_HTTP_POST;
    }

    @Override
    public Header[] getRequestHeader() {
        return super.getRequestHeader();
    }

    @Override
    public Map<String, Object> getRequestParams() {
        return super.getRequestParams();
    }
}
