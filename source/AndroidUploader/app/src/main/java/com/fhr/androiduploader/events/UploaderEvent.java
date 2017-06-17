package com.fhr.androiduploader.events;

import com.fhr.androiduploader.core.AndroidUploader;

import org.apache.http.client.HttpClient;

import java.util.EventObject;

/**
 * Android Uploader事件抽象基类
 * Created by fhr on 2017/6/2.
 */

public abstract class UploaderEvent extends EventObject {

    public UploaderEvent(AndroidUploader uploader){
        super(uploader);
        HttpClient client=null;
//        HttpURLConnection urlConnection=null;
//        urlConnection.setChunkedStreamingMode();
    }

    @Override
    public String toString() {
        return  String.format("%s[uploader=%s]",getClass().getName(),source);
    }
}
