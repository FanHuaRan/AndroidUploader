package fhr.com.androiduploader.types;

/**
 * 文件状态
 * Created by fhr on 2017/6/5.
 */

public enum FileStatus {
    //准备状态
    QUEUE,
    //上传中
    UPLOADING,
    //上传暂停
    PAUSED,
    //上传成功
    SUCCESS,
    //上传失败
    FAIIL

}
