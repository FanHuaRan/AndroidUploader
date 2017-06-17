package com.fhr.androiduploader.events.eventobjects;

import com.fhr.androiduploader.core.AndroidUploader;
import com.fhr.androiduploader.events.UploaderEvent;
import com.fhr.androiduploader.models.UploadFile;

/**
 * 某个文件开始上传事件
 * Created by fhr on 2017/6/2.
 */

public class UploadStartEvent extends UploaderEvent {
    /**
     * 文件
     */
    private final UploadFile file;

    public UploadStartEvent(AndroidUploader uploader,UploadFile file) {
        super(uploader);
        this.file = file;
    }

    public UploadFile getFile() {
        return file;
    }
}
