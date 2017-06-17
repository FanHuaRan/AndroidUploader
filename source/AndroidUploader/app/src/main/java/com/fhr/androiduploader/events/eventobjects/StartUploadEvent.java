package com.fhr.androiduploader.events.eventobjects;

import com.fhr.androiduploader.core.AndroidUploader;
import com.fhr.androiduploader.events.UploaderEvent;

/**
 * 开始上传流程事件，非单个文件上传
 * Created by fhr on 2017/6/2.
 */

public class StartUploadEvent extends UploaderEvent {
    /**
     * 文件上传总个数
     */
    private final  int totalCount;
    /**
     * 文件上传总大小
     */
    private final int totalSize;

    public StartUploadEvent(AndroidUploader uploader,int totalCount,int totalSize) {
        super(uploader);
        this.totalCount=totalCount;
        this.totalSize=totalSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getTotalSize() {
        return totalSize;
    }
}
