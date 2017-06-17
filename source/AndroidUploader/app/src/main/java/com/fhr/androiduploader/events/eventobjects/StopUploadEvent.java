package com.fhr.androiduploader.events.eventobjects;

import com.fhr.androiduploader.core.AndroidUploader;
import com.fhr.androiduploader.events.UploaderEvent;

/**
 * 文件上传流程暂停事件
 * Created by fhr on 2017/6/2.
 */

public class StopUploadEvent extends UploaderEvent {

    public StopUploadEvent(AndroidUploader uploader) {
        super(uploader);
    }
}
