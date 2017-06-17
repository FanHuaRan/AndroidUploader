package com.fhr.androiduploader.types;

/**
 * Uploader状态
 * Created by fhr on 2017/6/5.
 */

public enum  UploaderStatus{
    //准备状态，可以添加文件
    READY,
    //上传中
    UPLOADING,
    //上传暂停
    PAUSED,
    //上传结束
    FINISH
}
