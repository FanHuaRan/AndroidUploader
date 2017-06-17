package com.fhr.androiduploader.events.eventobjects;

import com.fhr.androiduploader.core.AndroidUploader;
import com.fhr.androiduploader.events.UploaderEvent;
import com.fhr.androiduploader.models.UploadFile;

/**
 * 一批文件加入队列之后事件
 * Created by fhr on 2017/6/2.
 */

public class FilesQueuedEvent  extends UploaderEvent {
    /**
     * 文件数组
     */
    private final UploadFile[] files;

    public FilesQueuedEvent(AndroidUploader uploader,UploadFile[] files) {
        super(uploader);
        this.files = files;
    }

    public UploadFile[] getFiles() {
        return files;
    }
}
