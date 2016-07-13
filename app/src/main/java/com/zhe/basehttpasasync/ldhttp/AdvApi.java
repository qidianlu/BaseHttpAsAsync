package com.zhe.basehttpasasync.ldhttp;

import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.zhe.basehttpasasync.util.JsonUtil;
import com.zhe.basehttpasasync.util.StartPic;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangyr on 2016/7/4.
 */
public class AdvApi extends LDRequest {


    private ArrayList<StartPic> list;

    @Override
    public Map<String, Object> getRequestParams() {
        Map<String, Object> map = new HashMap<>();
        map.put("width",414+"");
        map.put("height", 736+"");
        map.put("v",2+"");
        return map;
    }

    @Override
    public String getApiUrl() {
        return "common/adv";
    }

    @Override
    public void setResponseObject(Object object) {
        super.setResponseObject(object);
        if(error == null){
            JSONArray jsonObject = (JSONArray)object;
            list = (ArrayList<StartPic>) JsonUtil.parseJsonToList(jsonObject.toString(),new TypeToken<List<StartPic>>(){}.getType());
            Log.e("jsonObject:",jsonObject.toString());
        }
    }

    public ArrayList<StartPic> getList(){
        return list;
    }
}
