package fhr.com.androiduploader.types;

/**
 * 文件状态
 * Created by fhr on 2017/6/5.
 */

public enum FileStatus {
    //初始状态
    INITED,
    //已经进入文件队列,
    QUEUED,
    //上传中
    PROGRESS,
    //上传完成
    COMPLETE,
    //上传出错，可重试
    ERROR,
    //上传中断，可续传,这个实现比较困难，暂时不做
    //interrupt
    //文件被移除
    CANCELLED,
    //上传中
    UPLOADING,
    //上传暂停 这个实现比较困难，暂时不做
    //PAUSED,
    //上传成功
    SUCCESS,
    //上传失败
    FAIIL
}
