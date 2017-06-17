package com.fhr.androiduploader.events.eventobjects;

import com.fhr.androiduploader.core.AndroidUploader;
import com.fhr.androiduploader.events.UploaderEvent;

/**
 * uploader重置事件
 * Created by fhr on 2017/6/2.
 */

public class ResetEvent extends UploaderEvent {

    public ResetEvent(AndroidUploader uploader) {
        super(uploader);
    }

}
