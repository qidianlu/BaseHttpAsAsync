package com.zhe.basehttpasasync.ldhttp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * Created by zhangyr on 2016/7/6.
 */
public class LoadFileApi extends LDRequest {
    private String url;
    private Context context;
    private Bitmap bitmap;

    public LoadFileApi(Context context,String url){
        this.context = context;
        this.url = url;
    }

    @Override
    public String getBaseUrl() {
        return url;
    }

    @Override
    public void setResponseObject(Object object) {
        super.setResponseObject(object);
        if(error == null){
            byte[] binary = (byte[])object;
            Log.e("binarylength",binary.length+"---------");
            bitmap = getImage(binary);
        }
    }

    private Bitmap getImage(byte[] binaryData){
        return BitmapFactory.decodeByteArray(binaryData,0,binaryData.length);
    }

    public Bitmap getBitmap(){
        return bitmap;
    }
}
