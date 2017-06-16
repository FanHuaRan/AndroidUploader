package fhr.com.androiduploader.core.uploadengine;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import fhr.com.androiduploader.core.AndroidUploader;
import fhr.com.androiduploader.events.eventobjects.UploadErrorEvent;
import fhr.com.androiduploader.events.eventobjects.UploadSuccessEvent;
import fhr.com.androiduploader.models.UploadFile;

/**
 * 文件上传组件实现
 * Created by fhr on 2017/6/4.
 */

public class UploadEngineClass implements  UploadEngine {

    //文件处理游标
    private AtomicInteger cursor = null;

    //文件处理线程池
    private ExecutorService executorService = null;

    //上传控件
    private final AndroidUploader androidUploader;

    public UploadEngineClass(AndroidUploader androidUploader) {
        this.androidUploader = androidUploader;
    }

    public void startUpload() {
        //游标初始化为-1
        cursor = new AtomicInteger(-1);
        //全部新开线程处理上传逻辑
        int threadCount = androidUploader.getThread();
        executorService = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    UploadFile uploadFile = getNextUploadFile();
                    if (uploadFile != null) {
                        try {
                            uploadSingleFile(uploadFile);
                        } catch (FileNotFoundException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    public void uploadSingleFile(final  UploadFile uploadFile) throws FileNotFoundException {
        File file = new File(uploadFile.getFilePath());
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("uploladfile", file);
        params.put("name", uploadFile.getName());
        params.put("size", uploadFile.getSize());
        client.post(androidUploader.getServer(), params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int i, cz.msebera.android.httpclient.Header[] headers, byte[] bytes) {
                //上传完成
                androidUploader.fireUploadSuccess(new UploadSuccessEvent(androidUploader, uploadFile,new String(bytes)));
            }

            @Override
            public void onFailure(int i, cz.msebera.android.httpclient.Header[] headers, byte[] bytes, Throwable throwable) {
                //上传失败
                androidUploader.fireUploadError(new UploadErrorEvent(androidUploader, uploadFile,new String(bytes)));
            }

            @Override
            public void onProgress(long bytesWritten, long totalSize) {
                super.onProgress(bytesWritten, totalSize);
            }
        });
    }

    //获取下一个上传文件 线程安全
    private UploadFile getNextUploadFile() {
        //获取当前文件索引
        int currentIndex=cursor.incrementAndGet();
        if(currentIndex<androidUploader.getFileList().size()-1){
            return  androidUploader.getFileList().get(currentIndex);
        }else{
            return  null;
        }
    }
}


