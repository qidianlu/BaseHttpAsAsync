package com.zhe.basehttpasasync.util;

/**
 * Created by zhangyr on 2016/7/5.
 */
public class Regex {
    /**
     *  检查url是否合法
     * @param url
     * @return
     */
    public static boolean checkUrl(String url){
        return url.matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
    }
}
