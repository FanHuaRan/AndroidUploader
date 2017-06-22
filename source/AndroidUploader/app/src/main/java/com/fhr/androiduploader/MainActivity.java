package com.fhr.androiduploader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.fhr.androiduploader.core.AndroidUploader;
import com.fhr.androiduploader.events.eventobjects.StartUploadEvent;
import com.fhr.androiduploader.events.eventobjects.UploadCompleteEvent;
import com.fhr.androiduploader.events.eventobjects.UploadErrorEvent;
import com.fhr.androiduploader.events.eventobjects.UploadFinishedEvent;
import com.fhr.androiduploader.events.eventobjects.UploadProgressEvent;
import com.fhr.androiduploader.events.eventobjects.UploadStartEvent;
import com.fhr.androiduploader.events.eventobjects.UploadSuccessEvent;
import com.fhr.androiduploader.events.listeners.StartUploadListener;
import com.fhr.androiduploader.events.listeners.UploadCompleteListener;
import com.fhr.androiduploader.events.listeners.UploadErrorListener;
import com.fhr.androiduploader.events.listeners.UploadFinishedListener;
import com.fhr.androiduploader.events.listeners.UploadProgressListener;
import com.fhr.androiduploader.events.listeners.UploadStartListener;
import com.fhr.androiduploader.events.listeners.UploadSuccessListener;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    //上传控件
    private AndroidUploader androidUploader;
    {
        Map<String,Object> formData=new HashMap<>();
        formData.put("param1","1");
        formData.put("param2","hello2");
        androidUploader=new AndroidUploader("localhost:8080/uploadserver",false,1024*1024,3,5,100*10240,20*10240,null,formData);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //添加开始上传监听器
        androidUploader.addStartUploadListener(new StartUploadListener() {
            @Override
            public void HandleEvent(StartUploadEvent event) {
                Log.i("uploader", String.format("文件开始上传：大小：%d 个数：%d", event.getTotalSize(), event.getTotalCount()));
            }
        });
        //添加结束上传监听器
        androidUploader.addUploadFinishedListener(new UploadFinishedListener() {
            @Override
            public void HandleEvent(UploadFinishedEvent event) {
                Log.i("uploader", String.format("文件上传结束"));
            }
        });
        //添加文件进度监听器
        androidUploader.addUploadProgressListener(new UploadProgressListener() {
            @Override
            public void HandleEvent(UploadProgressEvent event) {
                Log.i("uploader", String.format("%s文件已经上传：%d", event.getFile().getName(), event.getPercentage()));
            }
        });
        //单文件开始上传
        androidUploader.addUploadStartListener(new UploadStartListener() {
            @Override
            public void HandleEvent(UploadStartEvent event) {
                Log.i("uploader", String.format("%s文件开始上传", event.getFile().getName()));
            }
        });
        //单文件上传完成 不论失败与否
        androidUploader.addUploadCompleteListener(new UploadCompleteListener() {
            @Override
            public void HandleEvent(UploadCompleteEvent event) {
                Log.i("uploader", String.format("%s文件上传完成", event.getFile().getName()));
            }
        });
        //单文件上传成功
        androidUploader.addUploadSuccessListener(new UploadSuccessListener() {
            @Override
            public void HandleEvent(UploadSuccessEvent event) {
                Log.i("uploader", String.format("%s文件上传成功", event.getFile().getName()));
            }
        });
        //单文件上传错误
        androidUploader.addUploadErrorListener(new UploadErrorListener() {
            @Override
            public void HandleEvent(UploadErrorEvent event) {
                Log.e("uploader", String.format("%s文件上传失败", event.getFile().getName(), event.getErrorCode()));
            }
        });
    }
    //添加文件
    private void chooseFileHandler(View source) {
      //  File file1 = new File("E:\\学习\\201401110121范华燃.xlsx");
        //File file2=new File("");
        // File file3=new File("");
       // androidUploader.addFile(file1);
//        androidUploader.addFile(file2);
//        androidUploader.addFile(file3);
    }
    //上传文件
    private void uploadHandler(View source) {
       // androidUploader.upload();
    }
}
