package com.fhr.androiduploader.core.uploadengine;

import com.fhr.androiduploader.core.uploadengine.UploadEngine;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import fhr.com.androiduploader.core.AndroidUploader;
import fhr.com.androiduploader.core.chunker.Chunker;
import fhr.com.androiduploader.core.chunker.ChunkerClass;
import fhr.com.androiduploader.events.eventobjects.UploadErrorEvent;
import fhr.com.androiduploader.events.eventobjects.UploadFinishedEvent;
import fhr.com.androiduploader.events.eventobjects.UploadProgressEvent;
import fhr.com.androiduploader.events.eventobjects.UploadStartEvent;
import fhr.com.androiduploader.events.eventobjects.UploadSuccessEvent;
import fhr.com.androiduploader.models.UploadFile;

/**
 * 文件上传组件实现
 * Created by fhr on 2017/6/4.
 */

public class UploadEngineClass implements UploadEngine {

    //文件处理游标
    private AtomicInteger cursor = null;

    //文件处理线程池
    // private ExecutorService executorService = null;

    //上传控件
    private final AndroidUploader androidUploader;

    //分片组件
    private final Chunker chunker;

    //http客户端组件
    private final AsyncHttpClient client;

    public UploadEngineClass(AndroidUploader androidUploader) {
        this.androidUploader = androidUploader;
        chunker = new ChunkerClass(androidUploader, androidUploader.getChunkedSize());
        client = new AsyncHttpClient();
    }
    @Override
    public void stopUpload() {

    }
    @Override
    public void startUpload() {
        //游标初始化为-1
        cursor = new AtomicInteger(-1);
        //上传并发数
        final int threadCount = androidUploader.getThread();
        for (int i = 0; i < threadCount; i++) {
               uploadNextFile();
         }
        //是否分片
//        final boolean chunked = androidUploader.getChunked();
//        //使用线程池
//        executorService = Executors.newFixedThreadPool(threadCount);
//        for (int i = 0; i < threadCount; i++) {
//            //如何阻塞？？
//            executorService.submit(new Runnable() {
//                @Override
//                public void run() {
//                    UploadFile uploadFile = getNextUploadFile();
//                    if (uploadFile != null) {
//                        try {
//                            uploadSingleFile(uploadFile,chunked);
//                        } catch (FileNotFoundException e1) {
//                            e1.printStackTrace();
//                        }
//                    }
//                }
//            });
              
    }

    /**
     * 上传下一个文件
     */
    private void uploadNextFile() {
        UploadFile uploadFile = getNextUploadFile();
        if (uploadFile != null) {
            //触发单文件开始上传事件
            androidUploader.fireUploadStart(new UploadStartEvent(androidUploader,uploadFile));
            try {
                uploadSingleFile(uploadFile, androidUploader.getChunked());
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 获取下一个上传文件 线程安全
     */
    private UploadFile getNextUploadFile() {
        //获取当前文件索引 incrementAndGet是原子操作
        int currentIndex=cursor.incrementAndGet();
        if(currentIndex<androidUploader.getFileList().size()-1){
            return  androidUploader.getFileList().get(currentIndex);
        }else if(currentIndex==androidUploader.getFileList().size()-1){
            //刚好上传完成 触发全部上传结束事件
            androidUploader.fireUploadFinished(new UploadFinishedEvent(androidUploader));
        }
        return null;
    }
    /**
     * 上传单个文件
     * @param uploadFile
     * @param chunked
     * @throws FileNotFoundException
     */
    public void uploadSingleFile(final  UploadFile uploadFile, final boolean chunked) throws FileNotFoundException {
//        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        //添加文件信息参数
        params.put("name", uploadFile.getName());
        params.put("size", uploadFile.getSize());
        //添加客户端自定义表单参数
        Map<String,Object> formData=androidUploader.getFormData();
        if(formData!=null){
            for(Map.Entry<String,Object> entry: formData.entrySet()){
                params.put(entry.getKey(),entry.getValue());
            }
        }
        //不分片
        if(!chunked){
            params.put("file", new File(uploadFile.getFilePath()));
            //上传文件
            client.post(androidUploader.getServer(), params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int i, cz.msebera.android.httpclient.Header[] headers, byte[] bytes) {
                    //上传完成 激发上传完成事件
                    androidUploader.fireUploadSuccess(new UploadSuccessEvent(androidUploader, uploadFile,new String(bytes)));
                    uploadNextFile();
                }
                @Override
                public void onFailure(int i, cz.msebera.android.httpclient.Header[] headers, byte[] bytes, Throwable throwable) {
                    //上传失败 激发上传失败事件
                    androidUploader.fireUploadError(new UploadErrorEvent(androidUploader, uploadFile,new String(bytes)));
                    uploadNextFile();
                }
                @Override
                public void onProgress(long bytesWritten, long totalSize) {
                    super.onProgress(bytesWritten, totalSize);
                    //上传进度改变，激发上传进度改变事件
                    int per = (int) ((bytesWritten * 1.0 / totalSize) * 100);
                    androidUploader.fireUploadProgress(new UploadProgressEvent(androidUploader,uploadFile,per));
                }
            });
        }
        //分片
    }

}


