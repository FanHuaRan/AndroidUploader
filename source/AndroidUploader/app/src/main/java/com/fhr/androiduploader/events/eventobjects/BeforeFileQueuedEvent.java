package com.fhr.androiduploader.events.eventobjects;

import com.fhr.androiduploader.core.AndroidUploader;
import com.fhr.androiduploader.events.UploaderEvent;
import com.fhr.androiduploader.models.UploadFile;

/**
 * 单个文件被加入队列之前事件
 * Created by fhr on 2017/6/2.
 */

public class BeforeFileQueuedEvent extends UploaderEvent {
    /**
     * 文件
     */
    private final  UploadFile file;

    public BeforeFileQueuedEvent(AndroidUploader uploader,UploadFile file) {
        super(uploader);
        this.file = file;
    }

    public UploadFile getFile() {
        return file;
    }
}
