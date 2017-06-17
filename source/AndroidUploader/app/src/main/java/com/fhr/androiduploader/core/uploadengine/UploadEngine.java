package com.fhr.androiduploader.core.uploadengine;

/**
 * 文件上传组件接口
 * Created by fhr on 2017/6/4.
 */

public interface UploadEngine {
    /**
     * 开始上传
     */
    void startUpload();

    /**
     * 停止上传
     */
    void stopUpload();
}
