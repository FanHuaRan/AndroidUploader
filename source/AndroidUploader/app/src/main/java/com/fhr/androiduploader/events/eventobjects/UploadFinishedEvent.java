package fhr.com.androiduploader.events.eventobjects;

import fhr.com.androiduploader.core.AndroidUploader;
import fhr.com.androiduploader.events.UploaderEvent;

/**
 * 所有文件上传结束事件
 * Created by fhr on 2017/6/2.
 */

public class UploadFinishedEvent extends UploaderEvent {

    public UploadFinishedEvent(AndroidUploader uploader) {
        super(uploader);
    }
}
