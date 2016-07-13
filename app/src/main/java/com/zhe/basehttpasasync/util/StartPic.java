package com.zhe.basehttpasasync.util;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/25 0025.
 */
public class StartPic implements Serializable {
    @Expose
    public int id;
    @Expose
    public String pic;
    @Expose
    public String starttime;
    @Expose
    public String endtime;
    @Expose
    public String localurl;


    @Override
    public String toString() {
        return "StartPic{" +
                "id=" + id +
                ", pic='" + pic + '\'' +
                ", starttime='" + starttime + '\'' +
                ", endtime='" + endtime + '\'' +
                ", localurl='" + localurl + '\'' +
                '}';
    }
}
