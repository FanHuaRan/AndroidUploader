package com.fhr.androiduploader.events.eventobjects;

import com.fhr.androiduploader.core.AndroidUploader;
import com.fhr.androiduploader.events.UploaderEvent;
import com.fhr.androiduploader.models.UploadFile;

/**
 * 单个文件上传过程进度事件
 * Created by fhr on 2017/6/2.
 */

public class UploadProgressEvent extends UploaderEvent {
    /**
     * 文件
     */
    private final UploadFile file;
    /**
     * 上传进度
     */
    private final int percentage;

    public UploadProgressEvent(AndroidUploader uploader,UploadFile file,int percentage) {
        super(uploader);
        this.file = file;
        this.percentage=percentage;
    }

    public UploadFile getFile() {
        return file;
    }

    public int getPercentage() {
        return percentage;
    }
}
