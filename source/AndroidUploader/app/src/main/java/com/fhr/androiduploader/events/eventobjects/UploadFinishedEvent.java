package com.fhr.androiduploader.events.eventobjects;

import com.fhr.androiduploader.core.AndroidUploader;
import com.fhr.androiduploader.events.UploaderEvent;

/**
 * 所有文件上传结束事件
 * Created by fhr on 2017/6/2.
 */

public class UploadFinishedEvent extends UploaderEvent {

    public UploadFinishedEvent(AndroidUploader uploader) {
        super(uploader);
    }
}
